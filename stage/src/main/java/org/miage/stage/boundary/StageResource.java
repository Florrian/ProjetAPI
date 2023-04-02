package org.miage.stage.boundary;

import org.miage.stage.entity.OffreStage;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "intervenants")
public interface StageResource extends JpaRepository<OffreStage, String> {
    // GET, POST, PUT, DELETE sont gérés automatiquement
}
