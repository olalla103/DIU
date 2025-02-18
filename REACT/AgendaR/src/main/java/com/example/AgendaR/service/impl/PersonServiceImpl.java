package com.example.AgendaR.service.impl;

import com.example.AgendaR.model.PersonDto;
import com.example.AgendaR.model.PersonVO;
import com.example.AgendaR.repository.PersonRepository;
import com.example.AgendaR.service.PersonService;
import com.example.AgendaR.util.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public PersonVO updatePerson(PersonVO personVO) {
        Optional<PersonDto> existingPersonOptional = personRepository.findById(personVO.getDNI());
        if (existingPersonOptional.isPresent()) {
            PersonDto existingPerson = getPersonDto(personVO, existingPersonOptional);
            PersonDto updatedPerson = personRepository.save(existingPerson);
            return PersonMapper.personMapperEntityToDto(updatedPerson);
        } else {
            return null; // or throw an exception if preferred
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
