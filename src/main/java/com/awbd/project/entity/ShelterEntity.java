package com.awbd.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "center")
public class ShelterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_center", insertable = false, updatable = false)
    private int idShelter;

    @Column(name = "shelter_name")
    private String shelterName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "capacity")
    private Integer capacity;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "shelterAnimal")
    private Set<AnimalEntity> animalsShelter;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "shelterEmployee")
    private List<EmployeeEntity> employeesShelter;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "shelterDonation")
    private List<DonationEntity> donationsShelter;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "shelterAdoption")
    private List<AdoptionEntity> adoptionsShelter;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "shelter_facility",
//            joinColumns = @JoinColumn(name = "id_facility",
//                    referencedColumnName = "id_facility"))
//    private List<FacilityEntity> facilities;


    public ShelterEntity(){

    }

    public ShelterEntity(int idShelter, String shelterName, String address, String city, String phone, Integer capacity) {
        this.idShelter = idShelter;
        this.shelterName = shelterName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.capacity = capacity;
    }

    public ShelterEntity(int idShelter, String shelterName, String address, String city, String phone, Integer capacity, Set<AnimalEntity> animalsShelter, List<EmployeeEntity> employeesShelter, List<DonationEntity> donationsShelter, List<AdoptionEntity> adoptionsShelter) {
        this.idShelter = idShelter;
        this.shelterName = shelterName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.capacity = capacity;
        this.animalsShelter = animalsShelter;
        this.employeesShelter = employeesShelter;
        this.donationsShelter = donationsShelter;
        this.adoptionsShelter = adoptionsShelter;
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

    public Set<AnimalEntity> getAnimalsShelter() {
        return animalsShelter;
    }

    public void setAnimalsShelter(Set<AnimalEntity> animalsShelter) {
        this.animalsShelter = animalsShelter;
    }

    public List<EmployeeEntity> getEmployeesShelter() {
        return employeesShelter;
    }

    public void setEmployeesShelter(List<EmployeeEntity> employeesShelter) {
        this.employeesShelter = employeesShelter;
    }

    public List<DonationEntity> getDonationsShelter() {
        return donationsShelter;
    }

    public void setDonationsShelter(List<DonationEntity> donationsShelter) {
        this.donationsShelter = donationsShelter;
    }

    public List<AdoptionEntity> getAdoptionsShelter() {
        return adoptionsShelter;
    }

    public void setAdoptionsShelter(List<AdoptionEntity> adoptionsShelter) {
        this.adoptionsShelter = adoptionsShelter;
    }

//    public List<FacilityEntity> getFacilities() {
//        return facilities;
//    }
//
//    public void setFacilities(List<FacilityEntity> facilities) {
//        this.facilities = facilities;
//    }
}
