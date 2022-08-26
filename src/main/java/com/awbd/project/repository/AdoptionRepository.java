package com.awbd.project.repository;

import com.awbd.project.entity.AdoptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRepository extends JpaRepository<AdoptionEntity, Integer> {


}
