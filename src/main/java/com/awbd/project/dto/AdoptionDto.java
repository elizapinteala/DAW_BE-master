package com.awbd.project.dto;

import com.awbd.project.validators.OnlyDigits;
import com.awbd.project.validators.OnlyLetters;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AdoptionDto {

    @NotNull
    private int idAdoption;
    @NotNull
    private LocalDate adoptionDate;
    @NotNull
    private Integer idShelterAdoption;

    @NotNull
    private String shelterName;
    @NotNull
    private Integer idAnimalAdoption;
    @NotNull
    private  String animalName;
    @NotNull
    private Integer idPersonAdoption;

    @NotNull
    @OnlyLetters
    private String firstNamePerson;

    @NotNull
    @OnlyLetters
    private String lastNamePerson;

    @NotNull
    @OnlyDigits
    private String phonePerson;

    public AdoptionDto(){

    }

    public AdoptionDto(@NotNull int idAdoption, @NotNull LocalDate adoptionDate, @NotNull Integer idShelterAdoption, @NotNull Integer idAnimalAdoption, @NotNull Integer idPersonAdoption, String firstNamePerson,  String lastNamePerson,  String phonePerson) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
        this.idShelterAdoption = idShelterAdoption;
        this.idAnimalAdoption = idAnimalAdoption;
        this.idPersonAdoption = idPersonAdoption;
        this.firstNamePerson = firstNamePerson;
        this.lastNamePerson = lastNamePerson;
        this.phonePerson = phonePerson;
    }

    public AdoptionDto(@NotNull int idAdoption, @NotNull LocalDate adoptionDate, @NotNull Integer idShelterAdoption, @NotNull Integer idAnimalAdoption, @NotNull Integer idPersonAdoption) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
        this.idShelterAdoption = idShelterAdoption;
        this.idAnimalAdoption = idAnimalAdoption;
        this.idPersonAdoption = idPersonAdoption;
    }

    public AdoptionDto(@NotNull int idAdoption, @NotNull LocalDate adoptionDate, @NotNull Integer idShelterAdoption, @NotNull String shelterName, @NotNull Integer idAnimalAdoption, @NotNull String animalName, @NotNull Integer idPersonAdoption, @NotNull String firstNamePerson, @NotNull String lastNamePerson, @NotNull String phonePerson) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
        this.idShelterAdoption = idShelterAdoption;
        this.shelterName = shelterName;
        this.idAnimalAdoption = idAnimalAdoption;
        this.animalName = animalName;
        this.idPersonAdoption = idPersonAdoption;
        this.firstNamePerson = firstNamePerson;
        this.lastNamePerson = lastNamePerson;
        this.phonePerson = phonePerson;
    }

    public int getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(int idAdoption) {
        this.idAdoption = idAdoption;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public Integer getIdShelterAdoption() {
        return idShelterAdoption;
    }

    public void setIdShelterAdoption(Integer idShelterAdoption) {
        this.idShelterAdoption = idShelterAdoption;
    }

    public Integer getIdAnimalAdoption() {
        return idAnimalAdoption;
    }

    public void setIdAnimalAdoption(Integer idAnimalAdoption) {
        this.idAnimalAdoption = idAnimalAdoption;
    }

    public Integer getIdPersonAdoption() {
        return idPersonAdoption;
    }

    public void setIdPersonAdoption(Integer idPersonAdoption) {
        this.idPersonAdoption = idPersonAdoption;
    }

    public String getFirstNamePerson() {
        return firstNamePerson;
    }

    public void setFirstNamePerson(String firstNamePerson) {
        this.firstNamePerson = firstNamePerson;
    }

    public String getLastNamePerson() {
        return lastNamePerson;
    }

    public void setLastNamePerson(String lastNamePerson) {
        this.lastNamePerson = lastNamePerson;
    }

    public String getPhonePerson() {
        return phonePerson;
    }

    public void setPhonePerson(String phonePerson) {
        this.phonePerson = phonePerson;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
}
