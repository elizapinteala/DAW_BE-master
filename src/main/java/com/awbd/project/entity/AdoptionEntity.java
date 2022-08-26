package com.awbd.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "adoption")
public class AdoptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adoption", insertable = false, updatable = false)
    private int idAdoption;

    @NotNull
    @Column(name = "adoption_date")
    private LocalDate adoptionDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_center")
    private ShelterEntity shelterAdoption;

    @Column(name = "shelter_name")
    private String shelterName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_animal")
    private AnimalEntity animalAdoption;

    @Column(name = "animal_name")
    private String animalName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private PersonEntity personAdoption;

    @Column(name = "first_person")
    private String firstNamePerson;

    @Column(name = "last_person")
    private String lastNamePerson;

    @Column(name = "phone_person")
    private String phonePerson;



    public AdoptionEntity(){

    }

    public AdoptionEntity(int idAdoption, LocalDate adoptionDate) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
    }

    public AdoptionEntity(int idAdoption, LocalDate adoptionDate, ShelterEntity shelterAdoption, AnimalEntity animalAdoption, PersonEntity personAdoption) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
        this.shelterAdoption = shelterAdoption;
        this.animalAdoption = animalAdoption;
        this.personAdoption = personAdoption;
    }

    public AdoptionEntity(int idAdoption, @NotNull LocalDate adoptionDate, ShelterEntity shelterAdoption, String shelterName, AnimalEntity animalAdoption, String animalName, PersonEntity personAdoption, String firstNamePerson, String lastNamePerson, String phonePerson) {
        this.idAdoption = idAdoption;
        this.adoptionDate = adoptionDate;
        this.shelterAdoption = shelterAdoption;
        this.shelterName = shelterName;
        this.animalAdoption = animalAdoption;
        this.animalName = animalName;
        this.personAdoption = personAdoption;
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

    public AnimalEntity getAnimalAdoption() {
        return animalAdoption;
    }

    public void setAnimalAdoption(AnimalEntity animalAdoption) {
        this.animalAdoption = animalAdoption;
    }

    public PersonEntity getPersonAdoption() {
        return personAdoption;
    }

    public void setPersonAdoption(PersonEntity personAdoption) {
        this.personAdoption = personAdoption;
    }

    public ShelterEntity getShelterAdoption() {
        return shelterAdoption;
    }

    public void setShelterAdoption(ShelterEntity shelterAdoption) {
        this.shelterAdoption = shelterAdoption;
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
}
