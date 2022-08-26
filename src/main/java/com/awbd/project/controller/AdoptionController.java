package com.awbd.project.controller;

import com.awbd.project.dto.AdoptionDto;
import com.awbd.project.entity.AdoptionEntity;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.exception.AdoptionException;
import com.awbd.project.service.AdoptionService;
import com.awbd.project.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {

    private static final Logger log = LoggerFactory.getLogger(AdoptionService.class);

    @Autowired
    private AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<AdoptionEntity> saveAdoption(@RequestBody @Valid AdoptionDto adoptionDto){
        log.info("Received request to create an adoption: {}", adoptionDto);
        AdoptionEntity adoptionEntity = adoptionService.saveAdoption(adoptionDto);
        return new ResponseEntity<>(adoptionEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byId/{id}")
    public ResponseEntity<AdoptionEntity> getAdoptionById(@PathVariable("id") Integer id){
        Optional<AdoptionEntity> adoptionEntity = adoptionService.getAdoptionById(id);
        log.info("Received request to get the adoption with id: ", id);
        if(adoptionEntity.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            return ResponseEntity.ok(adoptionEntity.get());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<AdoptionEntity>> getAll(){
        List<AdoptionEntity> adoptionEntities = adoptionService.getAll();
        return adoptionEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(adoptionEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<AdoptionEntity> updateAdoption(@PathVariable("id") Integer id, @RequestBody @Valid AdoptionDto adoptionDto){
        AdoptionEntity adoptionEntity = adoptionService.updateAdoption(id, adoptionDto);
        return new ResponseEntity<>(adoptionEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{idAdoption}")
    public ResponseEntity<Void> removeAdoption(@PathVariable Integer idAdoption){
        Optional<AdoptionEntity> adoptionEntity = adoptionService.getAdoptionById(idAdoption);
        if(adoptionEntity.isEmpty()){
            throw AdoptionException.adoptionNotFound();
        }
        else {
            adoptionService.removeAdoption(idAdoption);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

    }


}
