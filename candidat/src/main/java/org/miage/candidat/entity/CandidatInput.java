package main.java.org.miage.candidat.entity;

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
public class CandidatInput{

    @NotNull
    @NotBlank
    private String nom;

    @NotNull
    @NotBlank
    private String prenom;

    @NotNull
    @NotBlank
    @Size(min = 2, max= 2)
    private String age;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]{10}")
    private Integer telephone;

    @Size(min = 2)
    private String niveauEtudes;

    @NotNull
    @NotBlank
    private String experience;

    @NotNull
    @NotBlank
    private String email;


}
