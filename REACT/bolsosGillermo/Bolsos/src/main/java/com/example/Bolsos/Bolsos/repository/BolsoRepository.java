package com.example.Bolsos.Bolsos.repository;


import com.example.Bolsos.Bolsos.model.Bolso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BolsoRepository extends MongoRepository<Bolso,String> {
    List<Bolso>  findAll();
    Optional<Bolso> getTutorialById();
}
