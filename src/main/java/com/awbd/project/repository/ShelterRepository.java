package com.awbd.project.repository;

import com.awbd.project.entity.ShelterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterEntity, Integer> {


    Optional<ShelterEntity> findByAddress(String address);
}
