package org.miage.stage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreStage  implements Serializable{

  private static final long serialVersionUID = 6774930938047567575L;

    private int id;
    private String nomStage;
    private String domaine;
    private String nomOrganisation;
    private String descriptionStage;
    private Date datePublicationOffre;
    private String niveauEtudesStage;
    private String experienceRequiseStage;
    private Date dateDebutStage;
    private int dureeStage;
    private float salaireStage;
    private boolean indemnisation;
    private Organisation organisation;
    private LieuStage lieuStage;
  
    // constructeurs, getters, setters
  }