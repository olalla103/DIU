package com.example.MicroServicioAgenda.controller.impl;


import com.example.MicroServicioAgenda.controller.PersonAPI;
import com.example.MicroServicioAgenda.model.PersonDto;
import com.example.MicroServicioAgenda.model.PersonVO;
import com.example.MicroServicioAgenda.repository.PersonRepository;
import com.example.MicroServicioAgenda.service.PersonService;
import com.example.MicroServicioAgenda.util.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173") // Permitir solicitudes desde React (Vite)
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

    @PostMapping("/person")
    public PersonVO createPerson(@RequestBody PersonVO personVO) {
        return personService.addPerson(personVO);
    }

    @Override
    @GetMapping("/person/{DNI}")
    public PersonVO getPersonByDNI(@PathVariable String DNI) {
        return personService.getPersonByDNI(DNI).orElse(null);
    }

    @Override
    @GetMapping("/person/nombre/{nombre}")
    public List<PersonVO> findByNombreContaining(@PathVariable String nombre) {
        return personService.findByNombreContaining(nombre);
    }

    @Override
    public PersonVO updatePerson(PersonVO personVO) {
        Optional<PersonDto> existingPersonOptional = personRepository.findById(personVO.getDNI());
        if (existingPersonOptional.isPresent()) {
            PersonDto existingPerson = existingPersonOptional.get();
            existingPerson.setNombre(personVO.getNombre());
            existingPerson.setApellidos(personVO.getApellidos());
            existingPerson.setCalle(personVO.getCalle());
            existingPerson.setCodigoPostal(personVO.getCodigoPostal());
            existingPerson.setCiudad(personVO.getCiudad());
            existingPerson.setCumpleanios(personVO.getCumpleanios());
            PersonDto updatedPerson = personRepository.save(existingPerson);
            return PersonMapper.personMapperEntityToDto(updatedPerson);
        } else {
            return null;
        }
    }

    @Override
    @DeleteMapping("/person/{DNI}")
    public void deletePerson(@PathVariable String DNI) {
        personService.deletePerson(DNI);
    }

    @Override
    @DeleteMapping("/person")
    public void deleteAllPerson() {
        personService.deleteAllPerson();
    }


}