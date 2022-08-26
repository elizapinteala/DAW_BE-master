package com.awbd.project.service;


import com.awbd.project.dto.EmployeeDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.EmployeeEntity;
import com.awbd.project.entity.ShelterEntity;
import com.awbd.project.exception.AnimalException;
import com.awbd.project.exception.PersonException;
import com.awbd.project.exception.ShelterException;
import com.awbd.project.repository.EmployeeRepository;
import com.awbd.project.repository.ShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ShelterRepository shelterRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ShelterRepository shelterRepository) {
        this.employeeRepository = employeeRepository;
        this.shelterRepository = shelterRepository;
    }

    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setIdEmployee(employeeDto.getIdEmployee());
        employeeEntity.setType(employeeDto.getType());
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setEmail(employeeDto.getEmail());
        ShelterEntity shelterEntity = shelterRepository.findById(employeeDto.getIdCenterEmployee()).orElse(null);
        if(shelterEntity==null){
            throw ShelterException.shelterNotFound();
        }
        else {
            employeeEntity.setShelterEmployee(shelterEntity);

        }

        return employeeEntity;
    }

    @Transactional
    public EmployeeEntity saveEmployee(EmployeeDto employeeDto){
        Optional<EmployeeEntity> existingEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(existingEmployee.isPresent()){
            throw PersonException.personWithSameEmailAlreadyExists();
        }
        EmployeeEntity employeeEntity = dtoToEntity(employeeDto);
        log.info("Employee for add ", employeeDto);
        return employeeRepository.save(employeeEntity);

    }

    public Optional<EmployeeEntity> getEmployeeById(Integer id){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(employeeEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else {
            return employeeEntity;
        }
    }

    public Optional<EmployeeEntity> getEmployeeByEmail(String email){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByEmail(email);
        if(employeeEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else {
            return employeeEntity;
        }
    }

    public List<EmployeeEntity> getAll(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        if(employeeEntities.isEmpty()){
            throw PersonException.personNotFound();
        }
        else {
            return employeeEntities;
        }
    }



    @Transactional
    public void removeEmployee(Integer id){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(employeeEntity.isPresent()){
            employeeRepository.deleteById(id);
        }
        else {
            throw PersonException.personCouldNotBeRemoved();
        }
    }

    @Transactional
    public EmployeeEntity updateEmployee(Integer id, EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if(employeeEntity == null){
            throw PersonException.personNotFound();
        }
        else {
            employeeEntity.setType(employeeDto.getType());
            employeeEntity.setLastName(employeeDto.getLastName());
            if(employeeDto.getEmail().equals(employeeRepository.findByEmail(employeeDto.getEmail()))){
                throw PersonException.personWithSameEmailAlreadyExists();
            }
            else {
                employeeEntity.setEmail(employeeDto.getEmail());
            }
            if(shelterRepository.findById(employeeDto.getIdCenterEmployee()).orElse(null)==null){
                throw ShelterException.shelterNotFound();
            }
            else {
                employeeEntity.setShelterEmployee(shelterRepository.findById(employeeDto.getIdCenterEmployee()).orElse(null));
            }
        }
        return employeeRepository.save(employeeEntity);
    }
}
