package org.miage.stage.boundary;

import org.miage.stage.control.StageAssembler;
import org.miage.stage.entity.OffreStage;
import org.miage.stage.entity.OffreStageInput;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value= "/offreStage", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(OffreStage.class)
public class StageRepresentation{
    private final StageResource sr;
    private final StageAssembler sa;

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
    @GetMapping(value = "/stage/{idOffre}")
    public ResponseEntity<?> getOneOffreStage(@PathVariable("idOffre") String id){
        return Optional.of(sr.findById(id))
                .filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(sa.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Insert data
     * POST method
     * @param intervenant
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid OffreStageInput offreStage){
        Optional<OffreStageInput> body = Optional.ofNullable(offreStage);
        OffreStage toSave = new OffreStage(UUID.randomUUID().toString());
            offreStage.getNomStage();
            offreStage.getDomaine();
            offreStage.getNomOrganisation();
            offreStage.getDescriptionStage();
            offreStage.getDatePubOffre();
            offreStage.getNiveauEtudesStage();
            offreStage.getExpRequiseStage();
            offreStage.getDateDebStage();
            offreStage.getDureeStage();
            offreStage.getSalaireStage();
            offreStage.getIndemnisation();
            offreStage.getEmail();
            offreStage.getTelephone();
        OffreStage saved = sr.save(toSave);
        URI location = linkTo(StageRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Delete data by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/stage/{idOffre}")
    @Transactional
    public ResponseEntity<?> deleteOneOffreStage(@PathVariable("idOffre") String id, @RequestBody OffreStage offreStage){
        var stage = sr.findById(id);
        stage.ifPresent(sr::delete);
        return ResponseEntity.noContent().build();
    }

        /**
     * Update intervenant.
     * Bad request if body is empty.
     * Not found if id does not exist.
     * @param intervenant
     * @return
     */
    @PutMapping(value = "/stage/{idOffre}")
    @Transactional
    public ResponseEntity<?> updateOneOfrreStage(@PathVariable("idOffre") Integer id, @RequestBody OffreStage offreStage){
        Optional<OffreStage> body = Optional.ofNullable(offreStage);
        if(body.isEmpty()) return ResponseEntity.badRequest().build();
        if (!sr.existsById(id)) return ResponseEntity.notFound().build();
        offreStage.setIdOffre(id);
        sr.save(offreStage);
        return ResponseEntity.ok(offreStage);
    }
    
}