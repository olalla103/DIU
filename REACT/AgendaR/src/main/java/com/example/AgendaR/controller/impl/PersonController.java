package com.example.AgendaR.controller.impl;


import com.example.AgendaR.model.PersonDto;
import com.example.AgendaR.model.PersonVO;
import com.example.AgendaR.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5174") // Permitir acceso desde el frontend
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public List<PersonVO> getAllPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("/person")
    public PersonVO createPerson(@RequestBody PersonVO personVO) {
        return personService.addPerson(personVO);
    }

    @GetMapping("/person/{DNI}")
    public PersonVO getPersonByDNI(@PathVariable String DNI) {
        return personService.getPersonByDNI(DNI).orElse(null);
    }

    @DeleteMapping("/person/{DNI}")
    public void deletePerson(@PathVariable String DNI) {
        personService.deletePerson(DNI);
    }

    @DeleteMapping("/person")
    public void deleteAllPerson() {
        personService.deleteAllPerson();
    }


    @PutMapping("/person/{dni}")
    public PersonVO updatePerson(@RequestBody PersonVO personVO, @PathVariable String dni) {
        return personService.updatePerson(personVO, dni);
    }

}
