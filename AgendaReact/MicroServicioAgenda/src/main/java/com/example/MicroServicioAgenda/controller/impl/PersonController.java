package com.example.MicroServicioAgenda.controller.impl;


import com.example.MicroServicioAgenda.controller.PersonAPI;
import com.example.MicroServicioAgenda.model.PersonVO;
import com.example.MicroServicioAgenda.repository.PersonRepository;
import com.example.MicroServicioAgenda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController implements PersonAPI {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Override
    @GetMapping("/person")
    public List<PersonVO> getAllPerson() {
        return personService.getAllPerson();
    }

    @Override
    public PersonVO save(PersonVO personVO) {
        return null;
    }

}