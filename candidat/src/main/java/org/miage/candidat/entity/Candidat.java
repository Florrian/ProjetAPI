package main.java.org.miage.candidat.entity;

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
public class Candidat implements Serializable {

    private static final long serialVersionUID = 6774930938047567575L;

    @Id
    private String idCandidat;

    private String nom;
    private String prenom;
    private Integer age;
    private String telephone;
    private Integer niveauEtudes;
    private Boolean experience;
    private String email;
}