package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.OffreStage;


@Repository
public interface OffreStageRepository extends JpaRepository<OffreStage, Long> {
}

