//package com.awbd.project.controller;
//
//
//import com.awbd.project.dto.FacilityDto;
//import com.awbd.project.entity.FacilityEntity;
//import com.awbd.project.exception.FacilityException;
//import com.awbd.project.service.FacilityService;
//import com.awbd.project.service.ShelterService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/facility")
//public class FacilityController {
//
//    private static final Logger log = LoggerFactory.getLogger(FacilityService.class);
//
//    @Autowired
//    private FacilityService facilityService;
//
//    @Autowired
//    private ShelterService shelterService;
//
//    public FacilityController(FacilityService facilityService, ShelterService shelterService) {
//        this.facilityService = facilityService;
//        this.shelterService = shelterService;
//    }
//
////    @PostMapping()
////    public ResponseEntity<FacilityEntity> saveFacility(@Valid @RequestBody FacilityDto facilityDto){
////        log.info("Received request to create a facility: {}", facilityDto);
////        FacilityEntity facilityEntity = facilityService.addFacility(facilityDto);
////        return new ResponseEntity<>(facilityEntity, HttpStatus.CREATED);
////    }
//
//    @GetMapping("/params")
//    public ResponseEntity<Iterable<FacilityEntity>> getFacilities(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String schedule
//    ){
//        List<FacilityEntity> facilityEntities = facilityService.getFacilities(name, schedule);
//        return facilityEntities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(facilityEntities);
//    }
//
//    @GetMapping("/byId/{id}")
//    public ResponseEntity<FacilityEntity> getFacilityById(@PathVariable("id") Integer id){
//        FacilityEntity facilityEntity = facilityService.getFacilityById(id);
//        log.info("Received request to get facility with id: {}", id);
//        if(facilityEntity == null){
//            throw FacilityException.facilityDoesNotExist();
//        }
//        else {
//            return ResponseEntity.ok(facilityEntity);
//        }
//    }
//
////    @GetMapping("/byIdShelter/{id}")
////    public ResponseEntity<Iterable<FacilityEntity>> getFacilityByIdShelter(@PathVariable("id") Integer id){
////        List<FacilityEntity> facilityEntity = facilityService.findAllByIdHotel(id);
////        log.info("Received request to get facilities from the shelter with id: {}", id);
////        if(facilityEntity.isEmpty()){
////            throw FacilityException.facilityDoesNotExist();
////        }
////        else {
////            return ResponseEntity.ok(facilityEntity);
////        }
////    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<FacilityEntity> updateFacility(@PathVariable("id") Integer id, @RequestBody @Valid FacilityDto facilityDto){
//        FacilityEntity facilityEntity = facilityService.updateFacility(id, facilityDto);
//        return new ResponseEntity<>(facilityEntity, HttpStatus.OK);
//    }
//
//    @DeleteMapping()
//    public ResponseEntity<Void> removeFacility(@RequestParam Integer id){
//        FacilityEntity facilityEntity= facilityService.getFacilityById(id);
//        if(facilityEntity == null){
//            throw FacilityException.facilityDoesNotExist();
//        }
//        else {
//            facilityService.deleteFacility(id);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//        }
//
//    }
//
//
//
//
//}
