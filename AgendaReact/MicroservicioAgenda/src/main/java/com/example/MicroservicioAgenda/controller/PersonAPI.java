package com.example.MicroservicioAgenda.controller;

import com.example.MicroservicioAgenda.model.PersonVO;

import java.util.List;

public interface PersonAPI {

    List<PersonVO> getAllPerson();

    PersonVO save(PersonVO personVO);
}
