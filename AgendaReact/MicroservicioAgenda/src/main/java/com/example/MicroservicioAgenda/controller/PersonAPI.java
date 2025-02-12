package com.example.MicroServicioAgenda.controller;


import com.example.MicroServicioAgenda.model.PersonVO;

import java.util.List;

public interface PersonAPI {

    List<PersonVO> getAllPerson();

    PersonVO createPerson(PersonVO personVO);

    PersonVO getPersonByDNI(String DNI);

    List<PersonVO> findByNombreContaining(String nombre);

    PersonVO updatePerson(PersonVO personVO);

    void deletePerson(String DNI);

    void deleteAllPerson();

}
