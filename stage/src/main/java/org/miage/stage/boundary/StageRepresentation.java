package org.miage.stage.boundary;
import org.miage.stage.control.StageAssembler;
import org.miage.stage.entity.OffreStage;
import org.miage.stage.entity.OffreStageInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value= "/offreStage", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(OffreStage.class)
public class StageRepresentation{
    private final StageResource sr;
    private final StageAssembler sa;
    @Autowired
    private StageResource offreRepository;

    public StageRepresentation(StageResource sr, StageAssembler sa){
        this.sr = sr;
        this.sa = sa;
        
    }

     /**
     * Get all data
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllOffreStage(){
        return ResponseEntity.ok(sa.toCollectionModel(sr.findAll()));
    }  

    /**
     * Get data by id
     * @param integer
     * @return
     */    
    @GetMapping(value = "/{idOffre}")
    public ResponseEntity<?> getOneOffreStage(@PathVariable("idOffre") String id){
        return Optional.of(sr.findById(id))
                .filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(sa.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Insert data to create offre
     * POST method
     * @param intervenant
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid OffreStageInput offreStage){
        Optional<OffreStageInput> body = Optional.ofNullable(offreStage);
        OffreStage toSave = new OffreStage(UUID.randomUUID().toString());
        toSave.getNomStage();
        toSave.getDomaine();
        toSave.getNomOrganisation();
        toSave.getDescriptionStage();
        toSave.getDatePubOffre();
        toSave.getNiveauEtudeStage();
        toSave.getExpRequiseStage();
        toSave.getDateDebStage();
        toSave.getDureeStage();
        toSave.getSalaireStage();
        toSave.getIndemnisation();
        toSave.getEmail();
        toSave.getTelephone();
        OffreStage saved = sr.save(toSave);
        URI location = linkTo(StageRepresentation.class).slash(saved.getIdOffre()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Delete data by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{idOffre}")
    @Transactional
    public ResponseEntity<?> deleteOneOffreStage(@PathVariable("idOffre") String id, @RequestBody OffreStage offreStage){
        var stage = sr.findById(id);
        stage.ifPresent(sr::delete);
        return ResponseEntity.noContent().build();
    }

        /**
     * Update offre.
     * Bad request if body is empty.
     * Not found if id does not exist.
     * @param intervenant
     * @return
     */
    @PutMapping(value = "/updateOffre/{idOffre}")
    @Transactional
    public ResponseEntity<?> updateOneOfrreStage(@PathVariable("idOffre") String id, @RequestBody OffreStage updateOffre){
        OffreStage existingOffre = offreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("idOffre"));
        Optional<OffreStage> body = Optional.ofNullable(updateOffre);
        if(body.isEmpty()) return ResponseEntity.badRequest().build();
        if (!sr.existsById(id)) return ResponseEntity.notFound().build();
        existingOffre.setIdOffre(updateOffre.getIdOffre());
        existingOffre.setNomStage(updateOffre.getNomStage());
        existingOffre.setDomaine(updateOffre.getDomaine());
        existingOffre.setNomOrganisation(updateOffre.getNomOrganisation());
        existingOffre.setDescriptionStage(updateOffre.getDescriptionStage());
        existingOffre.setDatePubOffre(updateOffre.getDatePubOffre());
        existingOffre.setNiveauEtudeStage(updateOffre.getNiveauEtudeStage());
        existingOffre.setExpRequiseStage(updateOffre.getExpRequiseStage());
        existingOffre.setDateDebStage(updateOffre.getDateDebStage());
        existingOffre.setDureeStage(updateOffre.getDureeStage());
        existingOffre.setSalaireStage(updateOffre.getSalaireStage());
        existingOffre.setIndemnisation(updateOffre.getIndemnisation());
        existingOffre.setEmail(updateOffre.getEmail());
        existingOffre.setTelephone(updateOffre.getTelephone());
        sr.save(existingOffre);
        return ResponseEntity.ok(existingOffre);
    }
    

    /*  @GetMapping("/offres/{idOffre}/candidatures")
    public ResponseEntity<List<EntityModel<Candidature>>> getCandidaturesForOffre(@PathVariable String idOffre) {
    OffreStage offre = offreRepository.findById(idOffre).orElseThrow(() -> new ResourceNotFoundException("Offre de stage non trouvée avec l'identifiant : " + idOffre));

    List<EntityModel<Candidature>> candidatures = candidatureRepository.findByOffre(offre).stream()
            .map(candidatureModelAssembler::toModel)
            .collect(Collectors.toList());

    return ResponseEntity.ok(candidatures);
    }*/

    RestTemplate template;
	LoadBalancerClientFactory clientFactory;


	@CircuitBreaker(name = "candidat", fallbackMethod = "fallbackOffreStageCall")
	@Retry(name = "fallbackExemple", fallbackMethod = "fallbackOffreStageCall")
	@GetMapping("/offreStage/candidat/{source}/cible/{cible}")
	public OffreStage getCandidature(@PathVariable("source") String source, 
				@PathVariable("cible") String cible, @PathVariable BigDecimal qte) {

		RoundRobinLoadBalancer lb = clientFactory.getInstance("bourse-service", RoundRobinLoadBalancer.class);
		ServiceInstance instance = lb.choose().block().getServer();
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/offreStage/candidat/{source}/cible/{cible}";
		OffreStage response = template.getForObject(url, OffreStage.class, source, cible);
		return new OffreStage(response.getIdOffre(), source, cible, url, url, null, null, null, null, null, null, null, url, null, url);
	}

	private OffreStage fallbackOffreStageCall(RuntimeException re){
		System.out.println("Dans fallback");
		return new OffreStage("Unable to access the service");
	}
}