package com.awbd.project.service;


import com.awbd.project.dto.PersonDto;
import com.awbd.project.entity.PersonEntity;
import com.awbd.project.exception.PersonException;
import com.awbd.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity dtoToEntity(PersonDto personDto){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(personDto.getFirstName());
        personEntity.setLastName(personDto.getLastName());
        personEntity.setAddress(personDto.getAddress());
        personEntity.setCity(personDto.getCity());
        personEntity.setPhone(personDto.getPhone());
        personEntity.setEmail(personDto.getEmail());

        return personEntity;
    }

    @Transactional
    public PersonEntity savePerson(PersonDto personDto){
        Optional<PersonEntity> existingPerson = personRepository.findByEmail(personDto.getEmail());
        if(existingPerson.isPresent()){
            throw PersonException.personWithSameEmailAlreadyExists();
        }
        PersonEntity personEntity = dtoToEntity(personDto);
        log.info("Person for add ", personDto);
        return personRepository.save(personEntity);

    }

    public Optional<PersonEntity> getPersonById(Integer id){
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        if(personEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else{
            return personEntity;
        }
    }

    public Optional<PersonEntity> getPersonByEmail(String email){
        Optional<PersonEntity> personEntity = personRepository.findByEmail(email);
        if(personEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else {
            return personEntity;
        }
    }

    public List<PersonEntity> getPersons(String firstName, String lastName, String city){
        List<PersonEntity> personEntities = personRepository.findAll().stream()
                .filter(person -> isMatch(person, firstName, lastName, city))
                .collect(Collectors.toList());
        if(personEntities.isEmpty()){
            throw PersonException.personNotFound();
        }
        else {
            return personEntities.stream().collect(Collectors.toList());
        }
    }

    public List<PersonEntity> getAll(){
        List<PersonEntity> personEntities = personRepository.findAll();
        if(personEntities.isEmpty()){
            throw PersonException.personNotFound();
        }
        else {
            return personEntities;
        }
    }

    private boolean isMatch(PersonEntity person, String firstName, String lastName, String city){
        return(firstName == null || person.getFirstName().toLowerCase().equals(firstName.toLowerCase()))
        && (lastName == null || person.getLastName().toLowerCase().equals(lastName.toLowerCase()))
        && (city == null || person.getCity().toLowerCase().equals(city.toLowerCase()));
    }


    @Transactional
    public void removePerson(Integer id){
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        if(personEntity.isPresent()){
            personRepository.deleteById(id);
        }
        else {
            throw PersonException.personCouldNotBeRemoved();
        }
    }

    @Transactional
    public PersonEntity updatePerson(Integer id, PersonDto personDto){
        PersonEntity personEntity = personRepository.findById(id).orElse(null);
        if(personEntity == null){
            throw PersonException.personNotFound();
        }
        else{
            personEntity.setLastName(personDto.getLastName());
            personEntity.setAddress(personDto.getAddress());
            personEntity.setCity(personDto.getCity());
            if(personDto.getEmail().toString().equals(personRepository.findByEmail(personDto.getEmail().toString()))){
                throw PersonException.personWithSameEmailAlreadyExists();
            }
            else{
                personEntity.setEmail(personDto.getEmail());
            }
            personEntity.setPhone(personDto.getPhone());
        }
        return personRepository.save(personEntity);
    }
}
