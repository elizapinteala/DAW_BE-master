package com.awbd.project.dto;

import com.awbd.project.validators.OnlyLetters;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MedicalChartDto {

    @NotNull
    private int idMedical;
    @NotNull
    private LocalDate chartDate;

    private String disease;
    @NotNull
    private String treatment;
    @NotNull
    private int idAnimalMedical;

    @NotNull
    private String animalName;

    public MedicalChartDto(){

    }

    public MedicalChartDto(@NotNull int idMedical, @NotNull LocalDate chartDate, String disease, @NotNull String treatment, @NotNull int idAnimalMedical) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.treatment = treatment;
        this.idAnimalMedical = idAnimalMedical;
    }

    public MedicalChartDto(@NotNull int idMedical, @NotNull LocalDate chartDate, String disease, @NotNull String treatment, @NotNull int idAnimalMedical, String animalName) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.treatment = treatment;
        this.idAnimalMedical = idAnimalMedical;
        this.animalName = animalName;
    }

    public int getIdMedical() {
        return idMedical;
    }

    public void setIdMedical(int idMedical) {
        this.idMedical = idMedical;
    }

    public LocalDate getChartDate() {
        return chartDate;
    }

    public void setChartDate(LocalDate chartDate) {
        this.chartDate = chartDate;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getIdAnimalMedical() {
        return idAnimalMedical;
    }

    public void setIdAnimalMedical(int idAnimalMedical) {
        this.idAnimalMedical = idAnimalMedical;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
}
