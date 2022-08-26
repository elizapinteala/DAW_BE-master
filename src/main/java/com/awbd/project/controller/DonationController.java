package com.awbd.project.controller;

import com.awbd.project.dto.DonationDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.DonationEntity;
import com.awbd.project.exception.DonationException;
import com.awbd.project.service.AnimalService;
import com.awbd.project.service.DonationService;
import com.awbd.project.service.PersonService;
import com.awbd.project.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    private static final Logger log = LoggerFactory.getLogger(DonationService.class);

    @Autowired
    private DonationService donationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ShelterService shelterService;

    public DonationController(DonationService donationService, PersonService personService, ShelterService shelterService) {
        this.donationService = donationService;
        this.personService = personService;
        this.shelterService = shelterService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<DonationEntity> saveDonation(@Valid @RequestBody DonationDto donationDto){
        log.info("Received request to create a donation: {}", donationDto);
        DonationEntity donationEntity = donationService.saveDonation(donationDto);
        return new ResponseEntity<>(donationEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<DonationEntity> getDonation(@PathVariable("id") Integer id){
        DonationEntity donationEntity = donationService.getDonation(id);
        return ResponseEntity.ok(donationEntity);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<DonationEntity>> getAll(){
        List<DonationEntity> donationEntities = donationService.getAll();
        return donationEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(donationEntities);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<DonationEntity> updateDonation(@PathVariable("id") Integer id, @RequestBody @Valid DonationDto donationDto){
        DonationEntity donationEntity = donationService.updateDonation(id, donationDto);
        return new ResponseEntity<>(donationEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDonation(@PathVariable Integer id){
        DonationEntity donationEntity = donationService.getDonation(id);
        if(donationEntity == null){
            throw DonationException.donationNotFound();
        }
        else {
            donationService.removeDonation(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }
}
