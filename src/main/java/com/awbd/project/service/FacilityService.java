//package com.awbd.project.service;
//
//import com.awbd.project.dto.FacilityDto;
//import com.awbd.project.entity.FacilityEntity;
//import com.awbd.project.entity.ShelterEntity;
//import com.awbd.project.exception.FacilityException;
//import com.awbd.project.exception.ShelterException;
//import com.awbd.project.repository.FacilityRepository;
//import com.awbd.project.repository.ShelterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class FacilityService {
//    @Autowired
//    private FacilityRepository facilityRepository;
//
//    @Autowired
//    private ShelterRepository shelterRepository;
//
//    public FacilityService(FacilityRepository facilityRepository, ShelterRepository shelterRepository) {
//        this.facilityRepository = facilityRepository;
//        this.shelterRepository = shelterRepository;
//    }
//
//
//    public FacilityDto entityToDto(FacilityEntity facilityEntity) {
//        FacilityDto facilityDto = new FacilityDto();
//
//        facilityDto.setIdFacility(facilityEntity.getIdFacility());
//        facilityDto.setName(facilityEntity.getName());
//        facilityDto.setSchedule(facilityEntity.getSchedule());
//
//        return facilityDto;
//    }
//
//    public FacilityEntity addFacility(FacilityDto facilityDto) {
//
//        Optional<FacilityEntity> existingFacility = facilityRepository.findByName(facilityDto.getName());
//        if (existingFacility.isPresent()) {
//            throw FacilityException.facilityWithSameNameAlreadyExists();
//        }
//
//        FacilityEntity facilityEntitySaved = new FacilityEntity();
//
//        facilityEntitySaved.setIdFacility(facilityDto.getIdFacility());
//        facilityEntitySaved.setName(facilityDto.getName());
//        facilityEntitySaved.setSchedule(facilityDto.getSchedule());
//        facilityEntitySaved.setShelters(null);
//
//        return facilityRepository.save(facilityEntitySaved);
//    }
//
//    public List<FacilityEntity> getFacilities(String name, String schedule) {
//
//        List<FacilityEntity> facilityEntities = facilityRepository.findAll().stream()
//                .filter(facility -> isMatch(facility, name, schedule))
//                .collect(Collectors.toList());
//        if (facilityEntities.isEmpty()) {
//            throw FacilityException.facilityDoesNotExist();
//        }
//
//        return facilityEntities.stream().collect(Collectors.toList());
//    }
//
//
//    private boolean isMatch(FacilityEntity facility, String name, String schedule) {
//        return (name == null || Objects.equals(facility.getName(), name)
//        )&& (schedule == null || Objects.equals(facility.getSchedule(), schedule)
//        );
//    }
//
//    public FacilityEntity getFacilityById(int id) {
//        FacilityEntity facilityEntity = facilityRepository.findByIdFacility(id);
//        if (facilityEntity == null) {
//            throw FacilityException.facilityDoesNotExist();
//        }
//        return facilityEntity;
//    }
//
//    public List<FacilityEntity> findAllByIdHotel(int id) {
//        Optional<ShelterEntity> shelterEntity = shelterRepository.findById(id);
//        if (shelterEntity == null) {
//            throw ShelterException.shelterNotFound();
//        }
//        List<FacilityEntity> facilityEntities = shelterEntity.get().getFacilities();
//        if (facilityEntities.isEmpty()) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Shelter does not have facilities");
//        }
//        return facilityEntities;
//    }
//
//    public FacilityEntity updateFacility(int id, FacilityDto facilityDto) {
//        FacilityEntity facilityEntityUpdate = facilityRepository.findByIdFacility(id);
//        if (facilityEntityUpdate == null) {
//            throw FacilityException.facilityDoesNotExist();
//        }
//        facilityEntityUpdate.setName(facilityDto.getName());
//        facilityEntityUpdate.setSchedule(facilityDto.getSchedule());
//        return facilityRepository.save(facilityEntityUpdate);
//    }
//
//    public void deleteFacility(int id) {
//        FacilityEntity facilityEntity = facilityRepository.findByIdFacility(id);
//        if (facilityEntity == null) {
//            throw FacilityException.facilityDoesNotExist();
//        }
//        facilityRepository.deleteById(id);
//    }
//}
