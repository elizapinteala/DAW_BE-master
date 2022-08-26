package com.awbd.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "employee_table")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee", insertable = false, updatable = false)
    private int idEmployee;

    @Column(name = "employee_type")
    private String type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "employee_email", unique = true)
    private String email;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_center")
    private ShelterEntity shelterEmployee;


    public EmployeeEntity(){

    }

    public EmployeeEntity(int idEmployee, String type, String firstName, String lastName, String email) {
        this.idEmployee = idEmployee;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EmployeeEntity(int idEmployee, String type, String firstName, String lastName, String email, ShelterEntity shelterEmployee) {
        this.idEmployee = idEmployee;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.shelterEmployee = shelterEmployee;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ShelterEntity getShelterEmployee() {
        return shelterEmployee;
    }

    public void setShelterEmployee(ShelterEntity shelterEmployee) {
        this.shelterEmployee = shelterEmployee;
    }
}
