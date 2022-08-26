package com.awbd.project.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DonationDto {
    @NotNull
    private int idDonation;
    @NotNull
    private LocalDate donationDate;

    @NotNull
    private Double amount;
    @NotNull
    private int idCenterDonation;

    @NotNull
    private String shelterName;
    @NotNull
    private int idPersonDonation;
    @NotNull
    private String firstNamePerson;
    @NotNull
    private String lastNamePerson;



    public DonationDto(){

    }

    public DonationDto(@NotNull int idDonation, @NotNull LocalDate donationDate, @NotNull Double amount, @NotNull int idCenterDonation, @NotNull int idPersonDonation) {
        this.idDonation = idDonation;
        this.donationDate = donationDate;
        this.amount = amount;
        this.idCenterDonation = idCenterDonation;
        this.idPersonDonation = idPersonDonation;
    }

    public DonationDto(@NotNull int idDonation, @NotNull LocalDate donationDate, @NotNull Double amount, @NotNull int idCenterDonation, @NotNull String shelterName, @NotNull int idPersonDonation, @NotNull String firstNamePerson, @NotNull String lastNamePerson) {
        this.idDonation = idDonation;
        this.donationDate = donationDate;
        this.amount = amount;
        this.idCenterDonation = idCenterDonation;
        this.shelterName = shelterName;
        this.idPersonDonation = idPersonDonation;
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

    public int getIdCenterDonation() {
        return idCenterDonation;
    }

    public void setIdCenterDonation(int idCenterDonation) {
        this.idCenterDonation = idCenterDonation;
    }

    public int getIdPersonDonation() {
        return idPersonDonation;
    }

    public void setIdPersonDonation(int idPersonDonation) {
        this.idPersonDonation = idPersonDonation;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
