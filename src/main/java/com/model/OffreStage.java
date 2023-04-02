package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



@Table(name = "OffreStage")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OffreStage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Serial
    private static final long serialVersionUID = 1234567;

    @NotBlank
    @Column(length = 50)
    private String titre;

    @NotBlank
    private String description;

    @NotNull
    private Date dateDebut;

    @NotNull
    private Date dateFin;

}