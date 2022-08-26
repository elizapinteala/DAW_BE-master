package com.awbd.project.dto;

import com.awbd.project.validators.OnlyDigits;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ShelterDto {

    @NotNull
    private int idShelter;
    @NotNull
    private String shelterName;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    @OnlyDigits
    private String phone;
    @NotNull
    private Integer capacity;
    private List<AnimalDto> animals;
    private List<EmployeeDto> employees;
    private List<AdoptionDto> adoptions;
    private List<DonationDto> donations;

    public ShelterDto(){

    }

    public ShelterDto(@NotNull int idShelter, @NotNull String shelterName, @NotNull String address, @NotNull String city, @NotNull String phone, @NotNull Integer capacity, List<AnimalDto> animals, List<EmployeeDto> employees, List<AdoptionDto> adoptions, List<DonationDto> donations) {
        this.idShelter = idShelter;
        this.shelterName = shelterName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.capacity = capacity;
        this.animals = animals;
        this.employees = employees;
        this.adoptions = adoptions;
        this.donations = donations;
    }

    public int getIdShelter() {
        return idShelter;
    }

    public void setIdShelter(int idShelter) {
        this.idShelter = idShelter;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<AnimalDto> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDto> animals) {
        this.animals = animals;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    public List<AdoptionDto> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<AdoptionDto> adoptions) {
        this.adoptions = adoptions;
    }

    public List<DonationDto> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationDto> donations) {
        this.donations = donations;
    }
}
