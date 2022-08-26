package com.awbd.project.repository;

import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.MedicalChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MedicalChartRepository extends JpaRepository<MedicalChartEntity, Integer> {


    Optional<MedicalChartEntity> findByAnimalMedical(AnimalEntity animalMedical);
}
