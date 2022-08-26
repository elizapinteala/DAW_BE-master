package com.awbd.project.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person_table")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", insertable = false, updatable = false)
    private int idPerson;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "personDonation")
    private List<DonationEntity> donationsPerson;

    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "personAdoption")
    private List<AdoptionEntity> adoptionsPerson;

    public PersonEntity(){

    }

    public PersonEntity(int idPerson, String firstName, String lastName, String address, String city, String phone, String email) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }

    public PersonEntity(int idPerson, String firstName, String lastName, String address, String city, String phone, String email, List<DonationEntity> donationsPerson, List<AdoptionEntity> adoptionsPerson) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.donationsPerson = donationsPerson;
        this.adoptionsPerson = adoptionsPerson;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DonationEntity> getDonationsPerson() {
        return donationsPerson;
    }

    public void setDonationsPerson(List<DonationEntity> donationsPerson) {
        this.donationsPerson = donationsPerson;
    }

    public List<AdoptionEntity> getAdoptionsPerson() {
        return adoptionsPerson;
    }

    public void setAdoptionsPerson(List<AdoptionEntity> adoptionsPerson) {
        this.adoptionsPerson = adoptionsPerson;
    }
}
