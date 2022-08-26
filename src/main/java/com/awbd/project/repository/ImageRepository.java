package com.awbd.project.repository;

import com.awbd.project.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    Optional<ImageEntity> findByName(String name);

    Optional<ImageEntity> findByEntityName(String entityName);
}
