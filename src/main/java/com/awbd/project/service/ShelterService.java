package com.awbd.project.service;


import com.awbd.project.dto.ShelterDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.exception.ShelterException;
import com.awbd.project.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Transactional
    public ShelterEntity saveShelter(ShelterDto shelterDto){
        Optional<ShelterEntity> oneShelterEntity = shelterRepository.findByAddress(shelterDto.getAddress());
        if(oneShelterEntity.isPresent() && oneShelterEntity.get().getCity().equals(shelterDto.getCity())){
            throw ShelterException.shelterWithSameAddressAlreadyExists();
        }
        ShelterEntity shelterEntity = new ShelterEntity();
        shelterEntity.setShelterName(shelterDto.getShelterName());
        shelterEntity.setAddress(shelterDto.getAddress());
        shelterEntity.setCity(shelterDto.getCity());
        shelterEntity.setPhone(shelterDto.getPhone());
        shelterEntity.setCapacity(shelterDto.getCapacity());

        return shelterRepository.save(shelterEntity);
    }

    public List<ShelterEntity> getShelters(String shelterName, String address, String city, String phone, Integer capacity){
        List<ShelterEntity> shelterEntities = shelterRepository.findAll().stream()
                .filter(shelter -> isMatch(shelter, shelterName, address, city, phone, capacity))
                .collect(Collectors.toList());
        if(shelterEntities.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        return shelterEntities.stream().collect(Collectors.toList());
    }

    private boolean isMatch(ShelterEntity shelterEntity, String shelterName, String address, String city, String phone, Integer capacity){
        return (shelterName == null || shelterEntity.getShelterName().toLowerCase().equals(shelterName.toLowerCase()))
                && (address == null || shelterEntity.getAddress().toLowerCase().equals(address.toLowerCase()))
                && (city == null || shelterEntity.getCity().toLowerCase().equals(city.toLowerCase()))
                && (phone == null || shelterEntity.getPhone().equals(phone))
                && (capacity == null || shelterEntity.getCapacity().equals(capacity));
    }


    public Optional<ShelterEntity> getShelterById(Integer id){
        Optional<ShelterEntity> shelterEntity = shelterRepository.findById(id);
        if(shelterEntity.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        else {
            return shelterEntity;
        }
    }

    public List<ShelterEntity> getAll(){
        List<ShelterEntity> shelterEntities = shelterRepository.findAll();
        if(shelterEntities.isEmpty()){
            throw ShelterException.shelterNotFound();
        }
        else {
            return shelterEntities;
        }
    }

    @Transactional
    public ShelterEntity updateShelter(Integer id, ShelterDto shelterDto){
        ShelterEntity shelterEntity = shelterRepository.findById(id).orElse(null);
        if(shelterEntity == null){
            throw ShelterException.shelterNotFound();
        }
        Optional<ShelterEntity> existingShelter = shelterRepository.findByAddress(shelterDto.getAddress());
//        if(existingShelter.isPresent() && existingShelter.get().getCity().equals(shelterDto.getCity())){
//            throw ShelterException.shelterWithSameAddressAlreadyExists();
//        }
        if(shelterEntity.getAnimalsShelter().size() > shelterDto.getCapacity()){
            throw ShelterException.shelterNotUpdated();
        }
        shelterEntity.setShelterName(shelterDto.getShelterName());
        shelterEntity.setAddress(shelterDto.getAddress());
        shelterEntity.setCity(shelterDto.getCity());
        shelterEntity.setPhone(shelterDto.getPhone());
        shelterEntity.setCapacity(shelterDto.getCapacity());

        return shelterRepository.save(shelterEntity);

    }

    public void removeShelter(Integer id){
        ShelterEntity shelterEntity = shelterRepository.findById(id).orElse(null);
        if(shelterEntity == null){
            throw ShelterException.shelterNotFound();
        }
        shelterRepository.deleteById(id);
    }

}
