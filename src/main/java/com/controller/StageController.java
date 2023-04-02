package com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.OffreStage;
import com.repository.OffreStageRepository;


@RestController
// @RequestMapping("/offres-de-stage")
public class StageController {

    @GetMapping("/test")
	public String test() {
        return "test.html"; // peut renvoyer un template
    }

    @Autowired
    private OffreStageRepository offreStageRepository;

    @PostMapping
    public OffreStage createOffreDeStage(@Valid @RequestBody OffreStage offreStage) {
        return offreStageRepository.save(offreStage);
    }

    @GetMapping("/getoffre/{id}")
    public ResponseEntity<OffreStage> getOffreDeStageById(@PathVariable(value = "id") Long offreDeStageId) {
        Optional<OffreStage> offreDeStage = offreStageRepository.findById(offreDeStageId);
        if (offreDeStage.isPresent()) {
            return ResponseEntity.ok().body(offreDeStage.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateoffre/{id}")
    public ResponseEntity<OffreStage> updateOffreStage(@PathVariable(value = "id") Long offreStageId,
                                                           @Valid @RequestBody OffreStage offreStageDetails) {
        Optional<OffreStage> offreDeStage = offreStageRepository.findById(offreStageId);
        if (offreDeStage.isPresent()) {
            OffreStage updatedOffreStage = offreDeStage.get();
            updatedOffreStage.setTitre(offreStageDetails.getTitre());
            updatedOffreStage.setDescription(offreStageDetails.getDescription());
            updatedOffreStage.setDateDebut(offreStageDetails.getDateDebut());
            updatedOffreStage.setDateFin(offreStageDetails.getDateFin());
            OffreStage savedOffreStage = offreStageRepository.save(updatedOffreStage);
            return ResponseEntity.ok(savedOffreStage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteoffre/{id}")
    public ResponseEntity<Void> deleteOffreDeStage(@PathVariable(value = "id") Long offreStageId) {
        Optional<OffreStage> offreStage = offreStageRepository.findById(offreStageId);
        if (offreStage.isPresent()) {
            offreStageRepository.delete(offreStage.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<OffreStage> getAllOffresDeStage() {
        return offreStageRepository.findAll();
    }
}
