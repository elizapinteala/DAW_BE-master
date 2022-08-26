package com.awbd.project.service;


import com.awbd.project.dto.AdoptionDto;
import com.awbd.project.entity.AdoptionEntity;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.PersonEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.AdoptionException;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.exception.PersonException;
import com.awbd.project.exception.ShelterException;
import com.awbd.project.repository.AdoptionRepository;
import com.awbd.project.repository.AnimalRepository;
import com.awbd.project.repository.PersonRepository;
import com.awbd.project.repository.ShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService {


    private static final Logger log = LoggerFactory.getLogger(AdoptionService.class);


    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private AnimalService animalService;


    public AdoptionService(AdoptionRepository adoptionRepository, AnimalRepository animalRepository, PersonRepository personRepository, ShelterRepository shelterRepository) {
        this.adoptionRepository = adoptionRepository;
        this.animalRepository = animalRepository;
        this.personRepository = personRepository;
        this.shelterRepository = shelterRepository;
    }

    public AdoptionEntity dtoToEntity(AdoptionDto adoptionDto){
        AdoptionEntity adoptionEntity = new AdoptionEntity();
        adoptionEntity.setIdAdoption(adoptionDto.getIdAdoption());
        log.info("The id of the adoption is: ", adoptionDto.getIdAdoption());
        adoptionEntity.setAdoptionDate(adoptionDto.getAdoptionDate());
        log.info("The date of the adoption is: ", adoptionDto.getAdoptionDate());
        ShelterEntity shelterEntity = shelterRepository.findById(adoptionDto.getIdShelterAdoption()).orElse(null);
        if(shelterEntity == null){
            throw ShelterException.shelterNotFound();
        }
        adoptionEntity.setShelterAdoption(shelterRepository.findById(adoptionDto.getIdShelterAdoption()).orElse(null));
        if(shelterEntity.getShelterName() == null || !shelterEntity.getShelterName().equals(adoptionDto.getShelterName())){
            throw ShelterException.shelterNotFound();
        }
        adoptionEntity.setShelterName(adoptionDto.getShelterName());
        AnimalEntity animalEntity = animalRepository.findById(adoptionDto.getIdAnimalAdoption()).orElse(null);
        if(animalEntity == null){
            throw AnimalException.animalNotFound();
        }
        adoptionEntity.setAnimalAdoption(animalRepository.findById(adoptionDto.getIdAnimalAdoption()).orElse(null));
        if(animalEntity.getName()==null || !animalEntity.getName().equals(adoptionDto.getAnimalName())){
            throw AnimalException.animalNotFound();
        }
        adoptionEntity.setAnimalName(adoptionDto.getAnimalName());
        PersonEntity personEntity = personRepository.findById(adoptionDto.getIdPersonAdoption()).orElse(null);
        if(personEntity == null){
            throw PersonException.personNotFound();
        }
        adoptionEntity.setPersonAdoption(personRepository.findById(adoptionDto.getIdPersonAdoption()).get());
        if(personEntity.getFirstName()==null || !personEntity.getFirstName().equals(adoptionDto.getFirstNamePerson())){
            throw PersonException.personNotFound();
        }
        adoptionEntity.setFirstNamePerson(adoptionDto.getFirstNamePerson());

        if(personEntity.getLastName()==null || !personEntity.getLastName().equals(adoptionDto.getLastNamePerson())){
            throw PersonException.personNotFound();
        }
        adoptionEntity.setLastNamePerson(adoptionDto.getLastNamePerson());

        if(personEntity.getPhone()==null || !personEntity.getPhone().equals(adoptionDto.getPhonePerson())){
            throw PersonException.personNotFound();
        }
        adoptionEntity.setPhonePerson(adoptionDto.getPhonePerson());
        return adoptionEntity;
    }

    public AdoptionEntity saveAdoption(AdoptionDto adoptionDto){
        int ok=0;
        AdoptionEntity adoptionEntity = dtoToEntity(adoptionDto);

        LocalDate localDate = LocalDate.now();

        List<AnimalEntity> animalEntities = animalService.getAnimalByIdShelter(adoptionDto.getIdShelterAdoption());

        for(int i=0; i<animalEntities.size();i++){
            if(adoptionDto.getIdAnimalAdoption() == animalEntities.get(i).getIdAnimal()){
                log.info("You can find the animal you wish in the shelter you chose.");
                ok=1;
            }
        }
        if(ok==0){
            throw AnimalException.animalNotFound();
        }


        AnimalEntity animalEntity = animalRepository.findById(adoptionDto.getIdAnimalAdoption()).orElse(null);

        if(adoptionDto.getAdoptionDate().isBefore(animalEntity.getCheckInDate()) || adoptionDto.getAdoptionDate().isBefore(localDate)){
            throw AdoptionException.invalidAdoptionDate();
        }
        if(animalEntity.getStatus().toLowerCase().equals("UNDER_TREATMENT".toLowerCase())){
            throw AdoptionException.animalUnderTreatment();
        }
        if(animalEntity.getStatus().toLowerCase().equals("WANTED_FOR_ADOPTION".toLowerCase())){
            throw AdoptionException.animalAlreadyAdopted();
        }

        if(ok==1){
            animalEntity.setStatus("WANTED_FOR_ADOPTION");
        }
        else {
            throw AdoptionException.adoptionNotSaved();
        }

        return adoptionRepository.save(adoptionEntity);

    }

    public Optional<AdoptionEntity> getAdoptionById(Integer id){
        Optional<AdoptionEntity> adoptionEntity = adoptionRepository.findById(id);
        if(adoptionEntity.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        return adoptionEntity;
    }

    public List<AdoptionEntity> getAll(){
        List<AdoptionEntity> adoptionEntities = adoptionRepository.findAll();
        if(adoptionEntities.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            return adoptionEntities;
        }
    }

    @Transactional
    public void removeAdoption(Integer id){
        Optional<AdoptionEntity> adoptionEntity = adoptionRepository.findById(id);
        if(adoptionEntity.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            adoptionRepository.deleteById(id);
        }
    }

    @Transactional
    public AdoptionEntity updateAdoption(Integer id, AdoptionDto adoptionDto){
        AdoptionEntity adoptionEntity = dtoToEntity(adoptionDto);
        if(adoptionEntity == null){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            adoptionRepository.save(adoptionEntity);
        }
        return adoptionEntity;
    }






}
