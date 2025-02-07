package com.example.MicroServicioAgenda.controller;


import com.example.MicroServicioAgenda.model.PersonVO;

import java.util.List;

public interface PersonAPI {

    List<PersonVO> getAllPerson();

    PersonVO save(PersonVO personVO);
}
