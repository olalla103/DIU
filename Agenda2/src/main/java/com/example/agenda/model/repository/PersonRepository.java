package com.example.agenda.model.repository;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> ObtenerListaPersonas() throws ExceptionPerson;

    void addPersona(PersonVO personVO) throws ExceptionPerson;

    void editPersona(PersonVO personVO) throws ExceptionPerson;

    void deletePersona(PersonVO personVO) throws ExceptionPerson;

    void lastID() throws ExceptionPerson;
}
