package com.awbd.project.controller;

import com.awbd.project.dto.AnimalDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.service.AnimalService;
import com.awbd.project.service.MedicalChartService;
import com.awbd.project.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/animal")
public class AnimalController {

    private static final Logger log = LoggerFactory.getLogger(AnimalService.class);



    private AnimalService animalService;


    private MedicalChartService medicalChartService;


    private ShelterService shelterService;

    public AnimalController(AnimalService animalService, MedicalChartService medicalChartService, ShelterService shelterService) {
        this.animalService = animalService;
        this.medicalChartService = medicalChartService;
        this.shelterService = shelterService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<AnimalEntity> saveAnimal(@Valid @RequestBody AnimalDto animalDto){
        log.info("Received request to create an animal: {}", animalDto);
        AnimalEntity animalEntity = animalService.saveAnimal(animalDto);
        return new ResponseEntity<>(animalEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{idAnimal}")
    public ResponseEntity<AnimalEntity> getAnimalById(@PathVariable Integer idAnimal){
        AnimalEntity animalEntity = animalService.getAnimalById(idAnimal);
        log.info("Received request to get animal with id: {}", idAnimal);
        if(animalEntity == null){
            throw AnimalException.animalNotFound();
        }
        else {
            return ResponseEntity.ok(animalEntity);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/params")
    public ResponseEntity<Iterable<AnimalEntity>> getAnimals(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String specie,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) Integer shelterEntity
            ){
        List<AnimalEntity> animalEntities = animalService.getAnimals(status, age, gender, specie, breed, shelterEntity);
        return animalEntities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(animalEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<AnimalEntity>> getAll(){
        List<AnimalEntity> animalEntities = animalService.getAll();
        return animalEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(animalEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{idAnimal}")
    public ResponseEntity<AnimalEntity> updateAnimal(@PathVariable Integer idAnimal, @RequestBody @Valid AnimalDto editAnimal){
        AnimalEntity animalEntity = animalService.getAnimalById(idAnimal);
        if(animalEntity==null){
            throw AnimalException.animalNotFound();
        }
        animalService.updateAnimal(idAnimal, editAnimal);
        return new ResponseEntity<>(animalEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{idAnimal}")
    public ResponseEntity<Void> removeAnimal(@PathVariable Integer idAnimal){
        AnimalEntity animalEntity= animalService.getAnimalById(idAnimal);
        if(animalEntity == null){
            throw AnimalException.animalNotFound();
        }
        else {
            animalService.removeAnimal(idAnimal);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

    }
}
