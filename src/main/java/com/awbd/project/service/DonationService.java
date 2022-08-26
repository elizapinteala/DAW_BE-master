package com.awbd.project.service;

import com.awbd.project.dto.DonationDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.DonationEntity;
import com.awbd.project.entity.PersonEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.exception.DonationException;
import com.awbd.project.exception.PersonException;
import com.awbd.project.exception.ShelterException;
import com.awbd.project.repository.DonationRepository;
import com.awbd.project.repository.PersonRepository;
import com.awbd.project.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PersonRepository personRepository;

    public DonationService(DonationRepository donationRepository, ShelterRepository shelterRepository, PersonRepository personRepository) {
        this.donationRepository = donationRepository;
        this.shelterRepository = shelterRepository;
        this.personRepository = personRepository;
    }

    public DonationEntity dtoToEntity(DonationDto donationDto){
        DonationEntity donationEntity = new DonationEntity();
        donationEntity.setIdDonation(donationDto.getIdDonation());
        LocalDate localDate = LocalDate.now();
        if(donationDto.getDonationDate().isBefore(localDate)){
            throw DonationException.invalidDonationDate();
        }
        donationEntity.setDonationDate(donationDto.getDonationDate());
        if(donationDto.getAmount()<1){
            throw DonationException.amountCantBeLowerThanOne();
        }
        donationEntity.setAmount(donationDto.getAmount());
        Optional<ShelterEntity> shelterEntity = shelterRepository.findById(donationDto.getIdCenterDonation());
        if(shelterEntity.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        donationEntity.setShelterDonation(shelterEntity.get());
        if(shelterEntity.get().getShelterName()==null || !shelterEntity.get().getShelterName().equals(donationDto.getShelterName())){
            throw ShelterException.shelterNotFound();
        }
        donationEntity.setShelterName(donationDto.getShelterName());
        Optional<PersonEntity> personEntity = personRepository.findById(donationDto.getIdPersonDonation());
        if(personEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        donationEntity.setPersonDonation(personEntity.get());
        if(personEntity.get().getFirstName()==null || !personEntity.get().getFirstName().equals(donationDto.getFirstNamePerson())){
            throw PersonException.personNotFound();
        }
        donationEntity.setFirstNamePerson(donationDto.getFirstNamePerson());

        if(personEntity.get().getLastName()==null || !personEntity.get().getLastName().equals(donationDto.getLastNamePerson())){
            throw PersonException.personNotFound();
        }
        donationEntity.setLastNamePerson(donationDto.getLastNamePerson());
        return donationEntity;
    }

    @Transactional
    public DonationEntity saveDonation(DonationDto donationDto){
        DonationEntity donationEntity = dtoToEntity(donationDto);
        return donationRepository.save(donationEntity);
    }

    public DonationEntity getDonation(Integer id){
        Optional<DonationEntity> donationEntity = donationRepository.findById(id);
        if(donationEntity.isEmpty()){
            throw DonationException.donationNotFound();
        }
        else {
            return donationEntity.get();
        }
    }

    public List<DonationEntity> getAll(){
        List<DonationEntity> donationEntities = donationRepository.findAll();
        if(donationEntities.isEmpty()){
            throw DonationException.donationNotFound();
        }
        else {
            return donationEntities;
        }
    }

    public DonationEntity updateDonation(Integer id, DonationDto donationDto){
        Optional<DonationEntity> donationEntity = donationRepository.findById(id);
        if(donationEntity.isEmpty()){
            throw DonationException.donationNotFound();
        }
        donationEntity.get().setDonationDate(donationDto.getDonationDate());
        if(donationEntity.get().getAmount() == donationDto.getAmount()){
            throw DonationException.donationCantBeUpdated();
        }
        if(donationDto.getAmount()<1){
            throw DonationException.amountCantBeLowerThanOne();
        }
        donationEntity.get().setAmount(donationDto.getAmount());
        Optional<ShelterEntity> shelterEntity = shelterRepository.findById(donationDto.getIdCenterDonation());
        if(shelterEntity.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        donationEntity.get().setShelterDonation(shelterEntity.get());
        if(shelterEntity.get().getShelterName()==null || !shelterEntity.get().getShelterName().equals(donationDto.getShelterName())){
            throw ShelterException.shelterNotFound();
        }
        donationEntity.get().setShelterName(donationDto.getShelterName());
        Optional<PersonEntity> personEntity = personRepository.findById(donationDto.getIdPersonDonation());
        if(personEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        donationEntity.get().setPersonDonation(personEntity.get());
        if(personEntity.get().getFirstName()==null || !personEntity.get().getFirstName().equals(donationDto.getFirstNamePerson())){
            throw PersonException.personNotFound();
        }
        donationEntity.get().setFirstNamePerson(donationDto.getFirstNamePerson());

        if(personEntity.get().getLastName()==null || !personEntity.get().getLastName().equals(donationDto.getLastNamePerson())){
            throw PersonException.personNotFound();
        }
        donationEntity.get().setLastNamePerson(donationDto.getLastNamePerson());
        return donationEntity.get();
    }

    @Transactional
    public void removeDonation(Integer id){
        Optional<DonationEntity> donationEntity = donationRepository.findById(id);
        if(donationEntity.isEmpty()){
            throw DonationException.donationNotFound();
        }
        else {
            donationRepository.deleteById(id);
        }
    }


}
