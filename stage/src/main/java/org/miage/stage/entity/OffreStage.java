package org.miage.stage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreStage implements Serializable {

    public OffreStage(String string) {
    }
    private static final long serialVersionUID = 6774930938047567575L;

    @Id
    private String idOffre;

    private String nomStage;
    private String domaine;
    private String nomOrganisation;
    private String descriptionStage;
    private Date datePubOffre;
    private Integer niveauEtudeStage;
    private Boolean expRequiseStage;
    private Date dateDebStage;
    private Integer dureeStage;
    private Double salaireStage;
    private Boolean indemnisation;
    private String email;
    private Integer telephone;
    private String url;


   
}