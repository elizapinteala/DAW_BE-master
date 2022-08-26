package com.awbd.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donation")
public class DonationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donation", insertable = false, updatable = false)
    private int idDonation;

    @Column(name = "donation_date")
    private LocalDate donationDate;

    @Column(name = "amount")
    private Double amount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_center")
    private ShelterEntity shelterDonation;

    @Column(name = "shelter_name")
    private String shelterName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private PersonEntity personDonation;

    @Column(name = "first_person")
    private String firstNamePerson;

    @Column(name = "last_person")
    private String lastNamePerson;



    public DonationEntity(){

    }

    public DonationEntity(int idDonation, LocalDate donationDate, Double amount) {
        this.idDonation = idDonation;
        this.donationDate = donationDate;
        this.amount = amount;
    }

    public DonationEntity(int idDonation, LocalDate donationDate, Double amount, PersonEntity personDonation, ShelterEntity shelterDonation) {
        this.idDonation = idDonation;
        this.donationDate = donationDate;
        this.amount = amount;
        this.personDonation = personDonation;
        this.shelterDonation = shelterDonation;
    }

    public DonationEntity(int idDonation, LocalDate donationDate, Double amount, ShelterEntity shelterDonation, String shelterName, PersonEntity personDonation, String firstNamePerson, String lastNamePerson) {
        this.idDonation = idDonation;
        this.donationDate = donationDate;
        this.amount = amount;
        this.shelterDonation = shelterDonation;
        this.shelterName = shelterName;
        this.personDonation = personDonation;
        this.firstNamePerson = firstNamePerson;
        this.lastNamePerson = lastNamePerson;
    }

    public int getIdDonation() {
        return idDonation;
    }

    public void setIdDonation(int idDonation) {
        this.idDonation = idDonation;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PersonEntity getPersonDonation() {
        return personDonation;
    }

    public void setPersonDonation(PersonEntity personDonation) {
        this.personDonation = personDonation;
    }

    public ShelterEntity getShelterDonation() {
        return shelterDonation;
    }

    public void setShelterDonation(ShelterEntity shelterDonation) {
        this.shelterDonation = shelterDonation;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
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
}
