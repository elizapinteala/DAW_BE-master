package com.awbd.project.dto;

import com.awbd.project.validators.OnlyLetters;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AnimalDto {

    @NotNull
    private int idAnimal;
    @NotNull
    private String status;
    @NotNull
    @OnlyLetters
    private String name;
    private Integer age;
    @NotNull
    private String gender;
    @NotNull
    @OnlyLetters
    private String specie;
    @NotNull
    @OnlyLetters
    private String breed;
    @NotNull
    private LocalDate checkInDate;
    @NotNull
    private int idShelterAnimal;
    @NotNull
    private int idMedicalAnimal;
    @NotNull
    private int idAdoptionAnimal;

    public AnimalDto(){

    }

    public AnimalDto(@NotNull int idAnimal, @NotNull String status, @NotNull String name, Integer age, @NotNull String gender, @NotNull String specie, @NotNull String breed, @NotNull LocalDate checkInDate, @NotNull int idShelterAnimal, @NotNull int idMedicalAnimal, @NotNull int idAdoptionAnimal) {
        this.idAnimal = idAnimal;
        this.status = status;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.checkInDate = checkInDate;
        this.idShelterAnimal = idShelterAnimal;
        this.idMedicalAnimal = idMedicalAnimal;
        this.idAdoptionAnimal = idAdoptionAnimal;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getIdShelterAnimal() {
        return idShelterAnimal;
    }

    public void setIdShelterAnimal(int idShelterAnimal) {
        this.idShelterAnimal = idShelterAnimal;
    }

    public int getIdMedicalAnimal() {
        return idMedicalAnimal;
    }

    public void setIdMedicalAnimal(int idMedicalAnimal) {
        this.idMedicalAnimal = idMedicalAnimal;
    }

    public int getIdAdoptionAnimal() {
        return idAdoptionAnimal;
    }

    public void setIdAdoptionAnimal(int idAdoptionAnimal) {
        this.idAdoptionAnimal = idAdoptionAnimal;
    }
}
