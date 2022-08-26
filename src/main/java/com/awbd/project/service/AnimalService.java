package com.awbd.project.service;


import com.awbd.project.dto.AnimalDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.MedicalChartEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.exception.ShelterException;
import com.awbd.project.repository.AdoptionRepository;
import com.awbd.project.repository.AnimalRepository;
import com.awbd.project.repository.MedicalChartRepository;
import com.awbd.project.repository.ShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private static final Logger log = LoggerFactory.getLogger(AnimalService.class);

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private MedicalChartRepository medicalChartRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;



    public AnimalService(AnimalRepository animalRepository, ShelterRepository shelterRepository, MedicalChartRepository medicalChartRepository, AdoptionRepository adoptionRepository) {
        this.animalRepository = animalRepository;
        this.shelterRepository = shelterRepository;
        this.medicalChartRepository = medicalChartRepository;
        this.adoptionRepository = adoptionRepository;

    }

    //    public AnimalEntity dtoToEntity(AnimalDto animalDto){
//        AnimalEntity animalEntity = new AnimalEntity();
//        animalEntity.setIdAnimal(animalDto.getIdAnimal());
//        animalEntity.setStatus(animalDto.getStatus());
//        animalEntity.setName(animalDto.getName());
//        animalEntity.setAge(animalDto.getAge());
//        animalEntity.setGender(animalDto.getGender());
//        animalEntity.setSpecie(animalDto.getSpecie());
//        animalEntity.setBreed(animalDto.getBreed());
//        animalEntity.setCheckInDate(animalDto.getCheckInDate());
//        ShelterEntity shelterEntity = shelterRepository.findById(animalDto.getIdShelterAnimal()).orElse(null);
//        if(shelterEntity==null){
//            throw ShelterException.shelterNotFound();
//        }
//        else {
//            animalEntity.setShelterAnimal(shelterEntity);
//        }
//        MedicalChartEntity medicalChartEntity = medicalChartRepository.findById(animalDto.getIdMedicalAnimal()).orElse(null);
//        if(medicalChartEntity == null){
//            throw MedicalChartException.medicalChartNotFound();
//        }
//        else{
//            animalEntity.setMedicalAnimal(medicalChartEntity);
//        }
//        return animalEntity;
//    }

    @Transactional
    public AnimalEntity saveAnimal(AnimalDto animalDto){
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setIdAnimal(animalDto.getIdAnimal());
        animalEntity.setStatus(animalDto.getStatus());
        animalEntity.setName(animalDto.getName());
        animalEntity.setAge(animalDto.getAge());
        animalEntity.setGender(animalDto.getGender());
        animalEntity.setSpecie(animalDto.getSpecie());
        animalEntity.setBreed(animalDto.getBreed());
        animalEntity.setCheckInDate(animalDto.getCheckInDate());
        ShelterEntity shelterEntity = shelterRepository.findById(animalDto.getIdShelterAnimal()).orElse(null);
        if(shelterEntity==null){
            throw ShelterException.shelterNotFound();
        }
        if(shelterEntity.getCapacity() <= shelterEntity.getAnimalsShelter().size()){
            throw ShelterException.exceededCapacity();
        }
        animalEntity.setShelterAnimal(shelterEntity);

        MedicalChartEntity medicalChartEntity = medicalChartRepository.findById(animalDto.getIdMedicalAnimal()).orElse(null);
        animalEntity.setMedicalAnimal(medicalChartEntity);

        return animalRepository.save(animalEntity);

    }


    public List<AnimalEntity> getAnimals(String status, Integer age, String gender, String specie, String breed, Integer shelterEntity){
        List<AnimalEntity> animalEntities = animalRepository.findAll().stream()
                .filter(animal -> isMatch(animal, status, age, gender, specie, breed, shelterEntity))
                .collect(Collectors.toList());
        if(animalEntities.isEmpty()){
            throw AnimalException.animalNotFound();
        }
        return animalEntities.stream().collect(Collectors.toList());
    }

    private boolean isMatch(AnimalEntity animalEntity, String status, Integer age, String gender, String specie, String breed, Integer shelterEntity){
        return (status == null || animalEntity.getStatus().toLowerCase().equals(status.toLowerCase()))
                && (age == null || animalEntity.getAge().equals(age))
                && (gender == null || animalEntity.getGender().toString().toLowerCase().equals(gender.toString().toLowerCase()))
                && (specie == null || animalEntity.getSpecie().toLowerCase().equals(specie.toLowerCase()))
                && (breed == null || animalEntity.getBreed().toLowerCase().equals(breed.toLowerCase()))
                && (shelterEntity == null || animalEntity.getShelterAnimal().getIdShelter() == shelterEntity);

    }

    public AnimalEntity getAnimalById(Integer id){
        AnimalEntity animalEntity = animalRepository.findById(id).orElse(null);
        log.info("animal entity"+animalEntity);
        if(animalEntity == null){
            throw AnimalException.animalNotFound();
        }
        return animalEntity;

    }

    public List<AnimalEntity> getAll(){
        List<AnimalEntity> animalEntities = animalRepository.findAll();
        if(animalEntities.isEmpty()){
            throw AnimalException.animalNotFound();
        }
        else {
            return animalEntities;
        }
    }

    public List<AnimalEntity> getAnimalByIdShelter(Integer id){
        Optional<ShelterEntity> shelterEntity = shelterRepository.findById(id);
        if(shelterEntity.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        List<AnimalEntity> animalEntities = shelterEntity.get().getAnimalsShelter().stream().collect(Collectors.toList());
        return animalEntities;
    }

    @Transactional
    public AnimalEntity updateAnimal(Integer id, AnimalDto animalDto){
        AnimalEntity animalEntity = animalRepository.findById(id).orElse(null);
        if(animalEntity == null){
            throw AnimalException.animalNotFound();
        }
        animalEntity.setStatus(animalDto.getStatus());
        animalEntity.setName(animalDto.getName());
        animalEntity.setAge(animalDto.getAge());
        animalEntity.setGender(animalDto.getGender());
        animalEntity.setSpecie(animalDto.getSpecie());
        animalEntity.setBreed(animalDto.getBreed());
        animalEntity.setCheckInDate(animalDto.getCheckInDate());
        ShelterEntity shelterEntity = shelterRepository.findById(animalDto.getIdShelterAnimal()).orElse(null);
        if(shelterEntity==null){
            throw ShelterException.shelterNotFound();
        }
        else {
            animalEntity.setShelterAnimal(shelterEntity);
        }
//        MedicalChartEntity medicalChartEntity = medicalChartRepository.findById(animalDto.getIdMedicalAnimal()).orElse(null);
//        animalEntity.setMedicalAnimal(medicalChartEntity);
        return animalRepository.save(animalEntity);
    }

    @Transactional
    public void removeAnimal(Integer id){
        Optional<AnimalEntity> animalEntity = animalRepository.findById(id);
        if(animalEntity.isEmpty()){
            throw AnimalException.animalNotFound();
        }
        animalRepository.deleteById(id);
    }





}
