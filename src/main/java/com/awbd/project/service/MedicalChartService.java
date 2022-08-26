package com.awbd.project.service;

import com.awbd.project.dto.MedicalChartDto;
import com.awbd.project.dto.Treatment;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.MedicalChartEntity;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.exception.MedicalChartException;
import com.awbd.project.repository.AnimalRepository;
import com.awbd.project.repository.MedicalChartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalChartService {
    private static final Logger log = LoggerFactory.getLogger(MedicalChartService.class);

    @Autowired
    MedicalChartRepository medicalChartRepository;

    @Autowired
    AnimalRepository animalRepository;

    public MedicalChartService(MedicalChartRepository medicalChartRepository, AnimalRepository animalRepository) {
        this.medicalChartRepository = medicalChartRepository;
        this.animalRepository = animalRepository;
    }

    public MedicalChartEntity dtoToEntity(MedicalChartDto medicalChartDto){
        MedicalChartEntity medicalChartEntity = new MedicalChartEntity();
        medicalChartEntity.setIdMedical(medicalChartDto.getIdMedical());
        medicalChartEntity.setChartDate(medicalChartDto.getChartDate());
        medicalChartEntity.setDisease(medicalChartDto.getDisease());
        medicalChartEntity.setTreatment(medicalChartDto.getTreatment());
        AnimalEntity animalEntity = animalRepository.findById(medicalChartDto.getIdAnimalMedical()).orElse(null);
        if(animalEntity==null){
            throw AnimalException.animalNotFound();
        }
        else {
            medicalChartEntity.setAnimalMedical(animalEntity);
        }
        if(animalEntity.getName().isEmpty() || !animalEntity.getName().equals(medicalChartDto.getAnimalName())){
            throw AnimalException.animalNotFound();
        }
        medicalChartEntity.setAnimalName(medicalChartDto.getAnimalName());
        return medicalChartEntity;

    }

    @Transactional
    public MedicalChartEntity saveMedical(MedicalChartDto medicalChartDto){
        AnimalEntity animal = animalRepository.findById(medicalChartDto.getIdAnimalMedical()).orElse(null);
        if(animal == null){
            throw AnimalException.animalNotFound();
        }
        Optional<MedicalChartEntity> existingMedical = medicalChartRepository.findByAnimalMedical(animal);
        if(existingMedical.isPresent()){
            throw MedicalChartException.medicalChartAlreadyExists();
        }

        MedicalChartEntity medicalChartEntity = dtoToEntity(medicalChartDto);
        if(medicalChartDto.getChartDate().isBefore(medicalChartEntity.getAnimalMedical().getCheckInDate())){
            throw MedicalChartException.invalidChartDate();
        }
        log.info("Medical chart for add ", medicalChartDto);
        return medicalChartRepository.save(medicalChartEntity);
    }

    public Optional<MedicalChartEntity> getMedicalById(Integer id){
        Optional<MedicalChartEntity> medicalChartEntity = medicalChartRepository.findById(id);
        if(medicalChartEntity.isEmpty()){
           throw MedicalChartException.medicalChartNotFound();
        }
        else {
            return medicalChartEntity;
        }
    }

    public List<MedicalChartEntity> getAll(){
        List<MedicalChartEntity> medicalChartEntities = medicalChartRepository.findAll();
        if(medicalChartEntities.isEmpty()){
            throw MedicalChartException.medicalChartNotFound();
        }
        else {
            return medicalChartEntities;
        }
    }

//    public Optional<MedicalChartEntity> getMedicalByIdAnimal(Integer id){
//        Optional<MedicalChartEntity> medicalChartEntity = medicalChartRepository.findByAnimalMedical(id);
//        if(medicalChartEntity.isEmpty()){
//            throw AnimalException.animalNotFound();
//        }
//        else {
//            return medicalChartEntity;
//        }
//    }

    public List<MedicalChartEntity> getMedicals(LocalDate chartDate, String disease, String treatment, Integer animalEntity){
        List<MedicalChartEntity> medicalChartEntities = medicalChartRepository.findAll()
                .stream().filter(medical -> isMatch(medical, chartDate, disease, treatment, animalEntity))
                .collect(Collectors.toList());
        if(medicalChartEntities.isEmpty()){
            throw MedicalChartException.medicalChartNotFound();
        }
        else {
            return medicalChartEntities.stream().collect(Collectors.toList());
        }
    }

    private boolean isMatch(MedicalChartEntity medical, LocalDate chartDate, String disease, String treatment, Integer animalEntity){
        return (chartDate == null || medical.getChartDate().equals(chartDate))
                    && (disease == null || medical.getDisease().toLowerCase().equals(disease.toLowerCase()))
                    && (treatment == null || medical.getTreatment().toString().toLowerCase().equals(treatment.toString().toLowerCase()))
                    && (animalEntity == null || medical.getAnimalMedical().getIdAnimal() == animalEntity);

    }

    @Transactional
    public MedicalChartEntity updateMedical(Integer id, MedicalChartDto medicalChartDto){
        MedicalChartEntity medicalChartEntity = medicalChartRepository.findById(id).orElse(null);
        if(medicalChartEntity == null){
            throw MedicalChartException.medicalChartNotFound();
        }
        else {
            medicalChartEntity.setChartDate(medicalChartDto.getChartDate());
            medicalChartEntity.setDisease(medicalChartDto.getDisease());
            medicalChartEntity.setTreatment(medicalChartDto.getTreatment());
            Optional<AnimalEntity> animalEntity = animalRepository.findById(medicalChartDto.getIdAnimalMedical());
            if(animalRepository.findById(medicalChartDto.getIdAnimalMedical()).isEmpty()){
                throw AnimalException.animalNotFound();
            }
            medicalChartEntity.setAnimalMedical(animalRepository.findById(medicalChartDto.getIdAnimalMedical()).orElse(null));
            if(animalEntity.get().getName().toLowerCase()==null || !animalEntity.get().getName().equals(medicalChartDto.getAnimalName())){
                throw AnimalException.animalNotFound();
            }
            medicalChartEntity.setAnimalName(medicalChartDto.getAnimalName());
            return medicalChartRepository.save(medicalChartEntity);
        }
    }

    @Transactional
    public void removeMedical(Integer id){
        Optional<MedicalChartEntity> medicalChartEntity = medicalChartRepository.findById(id);
        if(medicalChartEntity.isEmpty()){
            throw MedicalChartException.medicalChartNotFound();
        }
        else {
            medicalChartRepository.deleteById(id);
        }
    }



}
