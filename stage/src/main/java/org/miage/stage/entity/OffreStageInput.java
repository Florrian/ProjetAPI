package org.miage.stage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffreStageInput{

    @NotNull
    @NotBlank
    private String nomStage;

    @NotNull
    @NotBlank
    private String domaine;

    @NotNull
    @NotBlank
    @Size(min = 2)
    private String nomOrganisation;

    @NotNull
    @NotBlank
    @Size(min = 350)
    private String descriptionStage;

    @NotNull
    @NotBlank
    private Date datePublicationOffre;

    @Size(min = 2)
    private String niveauEtudesStage;

    @NotNull
    @NotBlank
    private String experienceRequiseStage;

    @NotNull
    @NotBlank
    private Date dateDebutStage;

    @NotNull
    @NotBlank
    private int dureeStage;

    @Size(min = 0, max = 15)
    @Pattern(regexp = "[0-9]{15}")
    private float salaireStage;

    @Size(min = 0, max = 15)
    @Pattern(regexp = "[0-9]{15}")
    private boolean indemnisation;

    @NotNull
    @NotBlank
    private String email;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]{10}")
    private Integer telephone;

    //@NotNull
    //@NotBlank
    //private Organisation organisation;

    //@NotNull
    //@NotBlank
    //private LieuStage lieuStage;
}