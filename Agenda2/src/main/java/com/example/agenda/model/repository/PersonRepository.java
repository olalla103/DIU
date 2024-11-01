package com.example.agenda.model.repository;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> ObtenerListaPersonas() throws ExceptionPerson;

    void addPerson(PersonVO var1) throws ExceptionPerson;

    void deletePerson(Integer var1) throws ExceptionPerson;

    void editPerson(PersonVO var1) throws ExceptionPerson;

    int lastId() throws ExceptionPerson;
}
