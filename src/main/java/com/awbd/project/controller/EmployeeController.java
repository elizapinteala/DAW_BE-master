package com.awbd.project.controller;


import com.awbd.project.dto.EmployeeDto;
import com.awbd.project.dto.PersonDto;
import com.awbd.project.entity.AnimalEntity;
import com.awbd.project.entity.EmployeeEntity;
import com.awbd.project.entity.PersonEntity;
import com.awbd.project.exception.PersonException;
import com.awbd.project.service.EmployeeService;
import com.awbd.project.service.PersonService;
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
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<EmployeeEntity> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        log.info("Received request to create an employee: {}", employeeDto);
        EmployeeEntity employeeEntity = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employeeEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byId/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Integer id){
        Optional<EmployeeEntity> employeeEntity = employeeService.getEmployeeById(id);
        log.info("Received request to get employee with id: {}", id);
        if(employeeEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else{
            return ResponseEntity.ok(employeeEntity.get());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<EmployeeEntity> getEmployeeByEmail(@PathVariable("email") String email){
        Optional<EmployeeEntity> employeeEntity = employeeService.getEmployeeByEmail(email);
        log.info("Received request to get employee with email: {}", email);
        if(employeeEntity.isEmpty()){
            throw PersonException.personNotFound();
        }
        else{
            return ResponseEntity.ok(employeeEntity.get());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<Iterable<EmployeeEntity>> getAll(){
        List<EmployeeEntity> employeeEntities = employeeService.getAll();
        return employeeEntities.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(employeeEntities);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEmployeeById(@PathVariable Integer id){
        Optional<EmployeeEntity> employeeEntity = employeeService.getEmployeeById(id);
        if(employeeEntity.isPresent()){
            employeeService.removeEmployee(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            throw PersonException.personCouldNotBeRemoved();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(employeeEntity, HttpStatus.OK);
    }


}
