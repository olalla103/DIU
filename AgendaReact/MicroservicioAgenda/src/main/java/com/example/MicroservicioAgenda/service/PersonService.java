package com.example.MicroservicioAgenda.service;

import com.example.MicroservicioAgenda.model.PersonVO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonVO> getAllPerson();

    Optional<PersonVO> getPersonByDNI(String DNI);

    List<PersonVO> findByNombreContaining(String nombre);

    PersonVO addPerson(PersonVO person);

    PersonVO updatePerson(PersonVO person);

    ResponseEntity deletePerson(String DNI);

    ResponseEntity deleteAllPerson();
}
