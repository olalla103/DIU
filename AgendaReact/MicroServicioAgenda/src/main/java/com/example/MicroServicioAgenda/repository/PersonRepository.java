package com.example.MicroServicioAgenda.repository;

import com.example.MicroServicioAgenda.model.PersonDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<PersonDto, String> {

    List<PersonDto> findAll();

    Optional<PersonDto> getPersonByDNI();


}
