package main.java.org.miage.candidat.boundary;

import org.miage.bourseservice.entity.TauxChange;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CandidatResource extends JpaRepository<Candidat, Integer>{
    Candidat findBySourceAndCible(String source, String cible);
}
