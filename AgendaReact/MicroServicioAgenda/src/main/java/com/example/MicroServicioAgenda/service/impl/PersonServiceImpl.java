package com.example.MicroServicioAgenda.service.impl;

import com.example.MicroServicioAgenda.model.PersonDto;
import com.example.MicroServicioAgenda.model.PersonVO;
import com.example.MicroServicioAgenda.repository.PersonRepository;
import com.example.MicroServicioAgenda.service.PersonService;

import com.example.MicroServicioAgenda.util.PersonMapper;
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

    @Override
    public List<PersonVO> getAllPerson() {
        List<PersonDto> personDtoList = personRepository.findAll();
        return personDtoList.stream()
                .map(PersonMapper::personMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonVO> getPersonByDNI(String DNI) {
        return Optional.empty();
    }

    @Override
    public List<PersonVO> findByNombreContaining(String nombre) {
        return List.of();
    }

    @Override
    public PersonVO addPerson(PersonVO personVO) {
        PersonDto personDto = PersonMapper.personMapperDtoToEntity(personVO);
        PersonDto addedPersonEntity = personRepository.save(personDto);
        return PersonMapper.personMapperEntityToDto(addedPersonEntity);
    }

    @Override
    public PersonVO updatePerson(PersonVO person) {
        return null;
    }

    @Override
    public ResponseEntity deletePerson(String DNI) {
        return null;
    }

    @Override
    public ResponseEntity deleteAllPerson() {
        return null;
    }
}
