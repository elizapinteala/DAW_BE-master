package com.awbd.project.dto;

import com.awbd.project.validators.OnlyLetters;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FacilityDto {

    @NotNull
    private int idFacility;
    @NotNull
    @OnlyLetters
    private String name;
    @NotNull
    private String schedule;
    private List<ShelterDto> shelters;

    public FacilityDto(){}

    public FacilityDto(@NotNull int idFacility, @NotNull String name, @NotNull String schedule, List<ShelterDto> shelters) {
        this.idFacility = idFacility;
        this.name = name;
        this.schedule = schedule;
        this.shelters = shelters;
    }

    public int getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<ShelterDto> getShelters() {
        return shelters;
    }

    public void setShelters(List<ShelterDto> shelters) {
        this.shelters = shelters;
    }
}
