package com.example.agenda.model.repository;

import java.sql.Date;
import java.time.LocalDate;

public class PersonVO {
    // aquí tengo que añadir los atributos de la persona
    String firstName, lastName, street, city;
    Integer postalCode, id;
    LocalDate birthday;

    public PersonVO(String firstName, String lastName, String street, String city, Integer postalCode, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.birthday = birthday;
    }

    public PersonVO(Integer id, String firstName, String lastName, String street, String city, Integer postalCode, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", id=" + id +
                ", birthday=" + birthday +
                "\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getstreet() {
        return street;
    }

    public void setstreet(String street) {
        this.street = street;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    public Integer getpostalCode() {
        return postalCode;
    }

    public void setpostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getbirthday() {
        return birthday;
    }

    public void setbirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
