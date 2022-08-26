package com.awbd.project.repository;

import com.awbd.project.entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Integer> {


}
