package com.example.AgendaR.service.impl;

import com.example.AgendaR.model.PersonDto;
import com.example.AgendaR.model.PersonVO;
import com.example.AgendaR.repository.PersonRepository;
import com.example.AgendaR.service.PersonService;
import com.example.AgendaR.util.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    // BUSCAR A TODAS LAS PERSONAS
    @Override
    public List<PersonVO> getAllPerson() {
        return personRepository.findAll().stream()
                .map(PersonMapper::personMapperEntityToDto)
                .collect(Collectors.toList());
    }

    // BUSCAR A UNA PERSONA POR SU DNI
    @Override
    public Optional<PersonVO> getPersonByDNI(String DNI) {
        Optional<PersonDto> personDtoOptional = personRepository.getPersonByDNI(DNI);
        return personDtoOptional.map(PersonMapper::personMapperEntityToDto);
    }


    // BUSCAR A PERSONA POR SU NOMBRE
    @Override
    public List<PersonVO> findByNombreContaining(String nombre) {
        List<PersonDto> personDtoList = personRepository.findByNombreContaining(nombre);
        return personDtoList.stream()
                .map(PersonMapper::personMapperEntityToDto)
                .collect(Collectors.toList());
    }

    // AÑADIR A UNA PERSONA
    @Override
    public PersonVO addPerson(PersonVO personVO) {
        PersonDto personDto = PersonMapper.personMapperDtoToEntity(personVO);
        PersonDto savedPerson = personRepository.save(personDto);
        return PersonMapper.personMapperEntityToDto(savedPerson);
    }

    // ACTUALIZAR A UNA PERSONA
    @Override
    public PersonVO updatePerson(PersonVO personVO, String dni) {
        Optional<PersonDto> existingPersonOptional = personRepository.findById(dni);
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
            throw new RuntimeException("Persona no encontrada con DNI: " + dni);
        }
    }


    private static PersonDto getPersonDto(PersonVO personVO, Optional<PersonDto> existingPersonOptional) {
        PersonDto existingPerson = existingPersonOptional.get();
        existingPerson.setDNI(personVO.getDNI());
        existingPerson.setNombre(personVO.getNombre());
        existingPerson.setApellidos(personVO.getApellidos());
        existingPerson.setCalle(personVO.getCalle());
        existingPerson.setCodigoPostal(personVO.getCodigoPostal());
        existingPerson.setCiudad(personVO.getCiudad());
        existingPerson.setCumpleanios(personVO.getCumpleanios());
        return existingPerson;
    }

    @Override
    public PersonVO addTutorialToPerson(String DNI, String tutorialId) {
        String tutorialServiceUrl = "http://localhost:8080/api/v1/tutorials/" + tutorialId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(tutorialServiceUrl, Boolean.class);

        if (response.getBody() != null && response.getBody()) {
            Optional<PersonDto> existingPersonOptional = personRepository.findById(DNI);
            if (existingPersonOptional.isPresent()) {
                PersonDto person = existingPersonOptional.get();
                List<String> tutorials = person.getTutorialsIds();
                if (tutorials == null) {
                    tutorials = new ArrayList<>();
                }
                if (!tutorials.contains(tutorialId)) {
                    tutorials.add(tutorialId);
                }
                person.setTutorialsIds(tutorials);
                PersonDto updatedPerson = personRepository.save(person);
                return PersonMapper.personMapperEntityToDto(updatedPerson);
            }
        } else {
            throw new RuntimeException("El tutorial no existe en el microservicio de tutoriales.");
        }
        return null;
    }


    @Override
    public ResponseEntity<Void> deletePerson(String DNI) {
        personRepository.deleteById(DNI);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteAllPerson() {
        personRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
