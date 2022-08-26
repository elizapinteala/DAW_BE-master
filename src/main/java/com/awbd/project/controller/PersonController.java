package com.awbd.project.controller;


import com.awbd.project.dto.PersonDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.PersonEntity;
import com.awbd.project.exception.PersonException;
import com.awbd.project.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<PersonEntity> savePerson(@Valid @RequestBody PersonDto personDto){
        log.info("Received request to create a person: {}", personDto);
        PersonEntity personEntity = personService.savePerson(personDto);
        return new ResponseEntity<>(personEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byId/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable("id") Integer id){
        Optional<PersonEntity> personEntity = personService.getPersonById(id);
        log.info("Received request to get person with id: {}", id);
        if(personEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else{
            return ResponseEntity.ok(personEntity.get());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<PersonEntity> getPersonByEmail(@PathVariable("email") String email){
        Optional<PersonEntity> personEntity = personService.getPersonByEmail(email);
        if(personEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else{
            return ResponseEntity.ok(personEntity.get());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/params")
    public ResponseEntity<Iterable<PersonEntity>> getPersons(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String city
    ){
        List<PersonEntity> personEntities = personService.getPersons(firstName, lastName, city);
        return personEntities.isEmpty() ? ResponseEntity.noContent().build():
                ResponseEntity.ok(personEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<PersonEntity>> getAll(){
        List<PersonEntity> personEntities = personService.getAll();
        return personEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(personEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePersonById(@PathVariable Integer id){
        Optional<PersonEntity> personEntity = personService.getPersonById(id);
        if(personEntity.isPresent()){
            personService.removePerson(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            throw PersonException.personCouldNotBeRemoved();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable("id") Integer id, @Valid @RequestBody PersonDto personDto){
        PersonEntity personEntity = personService.updatePerson(id, personDto);
        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }
}
