package com.awbd.project.repository;

import com.awbd.project.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {


    Optional<PersonEntity> findByEmail(String email);

    void deletePersonByEmail(String email);
}
