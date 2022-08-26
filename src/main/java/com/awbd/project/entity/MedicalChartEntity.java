package com.awbd.project.entity;

import com.awbd.project.dto.Treatment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_chart")
public class MedicalChartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medical", insertable = false, updatable = false)
    private int idMedical;

    @Column(name = "chart_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate chartDate;

    @Column(name = "disease")
    private String disease;

    @Column(name = "treatment")
    private String treatment;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_animal")
    private AnimalEntity animalMedical;

    @Column(name = "animal_name")
    private String animalName;


    public MedicalChartEntity(){

    }

    public MedicalChartEntity(int idMedical, LocalDate chartDate, String disease, String treatment) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.treatment = treatment;
    }

    public MedicalChartEntity(int idMedical, LocalDate chartDate, String disease, String treatment, AnimalEntity animalMedical) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.treatment = treatment;
        this.animalMedical = animalMedical;
    }

    public MedicalChartEntity(int idMedical, LocalDate chartDate, String disease, String treatment, AnimalEntity animalMedical, String animalName) {
        this.idMedical = idMedical;
        this.chartDate = chartDate;
        this.disease = disease;
        this.treatment = treatment;
        this.animalMedical = animalMedical;
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

    public AnimalEntity getAnimalMedical() {
        return animalMedical;
    }

    public void setAnimalMedical(AnimalEntity animalMedical) {
        this.animalMedical = animalMedical;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
}
