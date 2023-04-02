package main.java.org.miage.candidat.boundary;

import org.miage.candidat.control.CandidatAssembler;
import org.miage.candidat.entity.Candidat;
import org.miage.candidat.entity.CandidatInput;
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
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;


public class CandidatRepresentation {
    private Environment environment;
    private CandidatResource tcr;
    
    @Autowired
    private OffreStageresource offreStageResource;

    @Autowired
    private CandidatureResource CandidatureResource;

    public CandidatRepresentation(Environment env, CandidatResource cr) {
        this.environment = env;
        this.cr = cr;
    }

    @GetMapping("/offreStage/candidature/{source}/cible/{cible}")
    public Candidat getCandidature(@PathVariable String source, @PathVariable String cible) {
        Candidat candidat = tcr.findBySourceAndCible(source, cible);
        candidat.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return candidat;
    }


    @GetMapping("/offres")
    public List<OffreStage> getOffres() {
        return offreStageRepository.findAll();
    }

    @GetMapping("/offres")
    public List<OffreStage> filterOffres(@RequestParam("domaine") String domaine,
                                          @RequestParam("dateDeb") String dateDeb,
                                          @RequestParam("organisation") String organisation,
                                          @RequestParam("pays") String pays,
                                          @RequestParam("ville") String ville) {
        return offreStageResource.findByDomaineAndDateDebAndOrganisationAndPaysAndVille(domaine, dateDeb, organisation, pays, ville);
    }

    @PostMapping("/offres/{idOffre}/postuler")
    public String postulerOffre(@PathVariable("idOffre") Long idOffre, @RequestBody Candidature candidature) {
        OffreStage offreStage = offreStageRepository.findById(idOffre).orElseThrow(() -> new ResourceNotFoundException("Offre de stage", "idOffre", idOffre));
        candidature.setOffreStage(offreStage);
        candidatureRepository.save(candidature);
        return "Candidature enregistrée avec succès";
    }

    @DeleteMapping("/candidatures/{idCandidat}")
    public String abandonnerCandidature(@PathVariable("idCandidat") Long idCandidature) {
        Candidature candidature = candidatureRepository.findById(idCandidature).orElseThrow(() -> new ResourceNotFoundException("Candidature", "idCandidature", idCandidature));
        candidatureRepository.delete(candidature);
        return "Candidature abandonnée avec succès";
    }


}
