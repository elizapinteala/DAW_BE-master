package com.awbd.project.controller;

import com.awbd.project.dto.MedicalChartDto;
import com.awbd.project.dto.Treatment;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.MedicalChartEntity;
import com.awbd.project.exception.MedicalChartException;
import com.awbd.project.exceptionHandler.MedicalChartExceptionHandler;
import com.awbd.project.service.MedicalChartService;
import com.awbd.project.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medical")
public class MedicalChartController {

    private static final Logger log = LoggerFactory.getLogger(MedicalChartService.class);

    @Autowired
    private MedicalChartService medicalChartService;

    public MedicalChartController(MedicalChartService medicalChartService) {
        this.medicalChartService = medicalChartService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<MedicalChartEntity> saveMedical(@Valid @RequestBody MedicalChartDto medicalChartDto){
        log.info("Received request to create a medical chart: {}", medicalChartDto);
        MedicalChartEntity medicalChartEntity = medicalChartService.saveMedical(medicalChartDto);
        return new ResponseEntity<>(medicalChartEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byId/{id}")
    public ResponseEntity<MedicalChartEntity> getMedicalById(@PathVariable("id") Integer id){
        Optional<MedicalChartEntity> medicalChartEntity = medicalChartService.getMedicalById(id);
        log.info("Received request to get the medical chart with id: {}", id);
        if(medicalChartEntity.isEmpty()){
            throw MedicalChartException.medicalChartNotFound();
        }
        else {
            return ResponseEntity.ok(medicalChartEntity.get());
        }
    }

//    @GetMapping("/byIdAnimal/{id}")
//    public ResponseEntity<MedicalChartEntity> getMedicalByIdAnimal(@PathVariable("id") Integer id){
//        Optional<MedicalChartEntity> medicalChartEntity = medicalChartService.getMedicalByIdAnimal(id);
//        log.info("Received request to get the medical chart for the animal with id: {}", id);
//        if(medicalChartEntity.isEmpty()){
//            throw MedicalChartException.medicalChartNotFound();
//        }
//        else {
//            return ResponseEntity.ok(medicalChartEntity.get());
//        }
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/params")
    public ResponseEntity<Iterable<MedicalChartEntity>> getMedicals(
            @RequestParam(required = false) LocalDate chartDate,
            @RequestParam(required = false) String disease,
            @RequestParam(required = false) String treatment,
            @RequestParam(required = false) Integer animalEntity
            ){
        List<MedicalChartEntity> medicalChartEntities = medicalChartService.getMedicals(chartDate, disease, treatment, animalEntity);
        return medicalChartEntities.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(medicalChartEntities.stream().collect(Collectors.toList()));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<MedicalChartEntity>> getAll(){
        List<MedicalChartEntity> medicalChartEntities = medicalChartService.getAll();
        return medicalChartEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(medicalChartEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalChartEntity> updateMedical(@PathVariable("id") Integer id, @Valid @RequestBody MedicalChartDto medicalChartDto){
        MedicalChartEntity medicalChartEntity = medicalChartService.updateMedical(id, medicalChartDto);
        return new ResponseEntity<>(medicalChartEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMedical(@PathVariable Integer id){
        Optional<MedicalChartEntity> medicalChartEntity = medicalChartService.getMedicalById(id);
        if(medicalChartEntity.isEmpty()){
            throw MedicalChartException.medicalChartNotFound();
        }
        else {
            medicalChartService.removeMedical(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

}
