package com.awbd.project.dto;

import com.awbd.project.validators.OnlyDigits;
import com.awbd.project.validators.OnlyLetters;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PersonDto {

    @NotNull
    private int idPerson;
    @NotNull
    @OnlyLetters
    private String firstName;
    @NotNull
    @OnlyLetters
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    @OnlyLetters
    private String city;
    @NotNull
    @OnlyDigits
    private String phone;
    @NotNull
    @Email
    private String email;
    private List<AdoptionDto> adoptions;
    private List<DonationDto> donations;

    public PersonDto(){

    }

    public PersonDto(@NotNull int idPerson, @NotNull String firstName, @NotNull String lastName, @NotNull String address, @NotNull String city, @NotNull String phone, @NotNull @Email String email, List<AdoptionDto> adoptions) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.adoptions = adoptions;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AdoptionDto> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<AdoptionDto> adoptions) {
        this.adoptions = adoptions;
    }
}
