package com.example.MicroservicioAgenda.repository;

import com.example.MicroservicioAgenda.model.PersonDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<PersonDto, String> {
    List<PersonDto> findByPublished();

    List<PersonDto> findAll();

    Optional<PersonDto> getPersonByDNI();

    List<PersonDto> findByTitleContaining(String title);

    List<PersonDto> findByPublished(Boolean published);
}
