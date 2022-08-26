package com.awbd.project.controller;

import com.awbd.project.dto.ShelterDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.ShelterException;
import com.awbd.project.service.ShelterService;
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


//@Slf4j
//@Controller

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private static final Logger log = LoggerFactory.getLogger(ShelterService.class);

    @Autowired
    private ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<ShelterEntity> saveShelter(@Valid @RequestBody ShelterDto shelterDto){
        log.info("Received request to create a shelter: {}", shelterDto);
        ShelterEntity shelterEntity = shelterService.saveShelter(shelterDto);
        return new ResponseEntity<>(shelterEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/params")
    public ResponseEntity<Iterable<ShelterEntity>> getShelters(
            @RequestParam(required = false) String shelterName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer capacity
    ){
        List<ShelterEntity> shelterEntityList = shelterService.getShelters(shelterName, address, city, phone, capacity);
        return shelterEntityList.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(shelterEntityList);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byId/{id}")
    public ResponseEntity<ShelterEntity> getShelterById(@PathVariable("id") Integer id){
        Optional<ShelterEntity> shelterEntity = shelterService.getShelterById(id);
        if(shelterEntity.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        else {
            return ResponseEntity.ok(shelterEntity.get());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<ShelterEntity>> getAll(){
        List<ShelterEntity> shelterEntities = shelterService.getAll();
        return shelterEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(shelterEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<ShelterEntity> updateShelter(@PathVariable("id") Integer id, @RequestBody ShelterDto shelterDto){
        ShelterEntity shelterEntity = shelterService.updateShelter(id, shelterDto);
        return new ResponseEntity<>(shelterEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeShelter(@PathVariable Integer id){
        Optional<ShelterEntity> shelterEntity = shelterService.getShelterById(id);
        if(shelterEntity.isPresent()){
            shelterService.removeShelter(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            throw ShelterException.shelterCouldNotBeDeleted();
        }
    }




}
