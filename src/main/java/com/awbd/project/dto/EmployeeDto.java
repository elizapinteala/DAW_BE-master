package com.awbd.project.dto;

import com.awbd.project.validators.OnlyLetters;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmployeeDto {

    @NotNull
    private int idEmployee;
    @NotNull
    private String type;
    @NotNull
    @OnlyLetters
    private String firstName;
    @NotNull
    @OnlyLetters
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private int idCenterEmployee;

    public EmployeeDto(){

    }

    public EmployeeDto(@NotNull int idEmployee, @NotNull String type, @NotNull String firstName, @NotNull String lastName, @NotNull @Email String email, @NotNull int idCenterEmployee) {
        this.idEmployee = idEmployee;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idCenterEmployee = idCenterEmployee;
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

    public int getIdCenterEmployee() {
        return idCenterEmployee;
    }

    public void setIdCenterEmployee(int idCenterEmployee) {
        this.idCenterEmployee = idCenterEmployee;
    }
}
