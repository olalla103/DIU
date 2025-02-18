package com.example.AgendaR.repository;


import com.example.AgendaR.model.PersonDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<PersonDto, String> {

    List<PersonDto> findAll();

    Optional<PersonDto> getPersonByDNI(String DNI);

    List<PersonDto> findByNombreContaining(String nombre);

//    PersonDto addPerson(PersonDto person);
//
//    PersonDto updatePerson(PersonDto person);
//
//    void deletePerson(String DNI);
//
//    void deleteAllPerson();

}
