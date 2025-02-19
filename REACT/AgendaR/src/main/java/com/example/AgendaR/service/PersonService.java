package com.example.AgendaR.service;


import com.example.AgendaR.model.PersonVO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonVO> getAllPerson();

    Optional<PersonVO> getPersonByDNI(String DNI);

    List<PersonVO> findByNombreContaining(String nombre);

    PersonVO addPerson(PersonVO person);

    PersonVO updatePerson(PersonVO person,String DNI);

    ResponseEntity deletePerson(String DNI);

    ResponseEntity deleteAllPerson();

}
