//package com.awbd.project.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "facility")
//public class FacilityEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_facility", insertable = false, updatable = false)
//    private Integer idFacility;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "schedule")
//    private String schedule;
//
//    @ManyToMany(mappedBy = "facilities", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<ShelterEntity> shelters;
//
//    public FacilityEntity(){}
//
//    public FacilityEntity(Integer idFacility, String name, String schedule, List<ShelterEntity> shelters) {
//        this.idFacility = idFacility;
//        this.name = name;
//        this.schedule = schedule;
//        this.shelters = shelters;
//    }
//
//    public FacilityEntity(Integer idFacility, String name, String schedule) {
//        this.idFacility = idFacility;
//        this.name = name;
//        this.schedule = schedule;
//    }
//
//    public FacilityEntity(String name, String schedule) {
//        this.name = name;
//        this.schedule = schedule;
//    }
//
//    public Integer getIdFacility() {
//        return idFacility;
//    }
//
//    public void setIdFacility(Integer idFacility) {
//        this.idFacility = idFacility;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(String schedule) {
//        this.schedule = schedule;
//    }
//
//    public List<ShelterEntity> getShelters() {
//        return shelters;
//    }
//
//    public void setShelters(List<ShelterEntity> shelters) {
//        this.shelters = shelters;
//    }
//}
