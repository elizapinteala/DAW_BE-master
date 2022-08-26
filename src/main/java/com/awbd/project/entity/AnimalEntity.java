package com.awbd.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animal")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal", insertable = false, updatable = false)
    private int idAnimal;

    @Column(name = "status")
    private String status;

    @Column(name = "name_animal")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "specie")
    private String specie;

    @Column(name = "breed")
    private String breed;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @JsonIgnore
    @OneToOne(mappedBy = "animalMedical", cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
    private MedicalChartEntity medicalAnimal;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "animalAdoption")
    private List<AdoptionEntity> adoptionsAnimal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_center")
    private ShelterEntity shelterAnimal;


    public AnimalEntity(){

    }

    public AnimalEntity(int idAnimal, String status, String name, Integer age, String gender, String specie, String breed, LocalDate checkInDate) {
        this.idAnimal = idAnimal;
        this.status = status;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.checkInDate = checkInDate;
    }

    public AnimalEntity(int idAnimal, String status, String name, Integer age, String gender, String specie, String breed, LocalDate checkInDate, MedicalChartEntity medicalAnimal, List<AdoptionEntity> adoptionsAnimal, ShelterEntity shelterAnimal) {
        this.idAnimal = idAnimal;
        this.status = status;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specie = specie;
        this.breed = breed;
        this.checkInDate = checkInDate;
        this.medicalAnimal = medicalAnimal;
        this.adoptionsAnimal = adoptionsAnimal;
        this.shelterAnimal = shelterAnimal;
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

    public MedicalChartEntity getMedicalAnimal() {
        return medicalAnimal;
    }

    public void setMedicalAnimal(MedicalChartEntity medicalAnimal) {
        this.medicalAnimal = medicalAnimal;
    }

    public List<AdoptionEntity> getAdoptionsAnimal() {
        return adoptionsAnimal;
    }

    public void setAdoptionsAnimal(List<AdoptionEntity> adoptionsAnimal) {
        this.adoptionsAnimal = adoptionsAnimal;
    }

    public ShelterEntity getShelterAnimal() {
        return shelterAnimal;
    }

    public void setShelterAnimal(ShelterEntity shelterAnimal) {
        this.shelterAnimal = shelterAnimal;
    }


}
