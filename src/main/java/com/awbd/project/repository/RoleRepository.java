package com.awbd.project.repository;

import com.awbd.project.entity.Role;
import com.awbd.project.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(ERole name);
}
