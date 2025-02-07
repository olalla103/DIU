package com.example.MicroServicioAgenda.util;

import com.example.MicroServicioAgenda.model.PersonDto;
import com.example.MicroServicioAgenda.model.PersonVO;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonDto personMapperDtoToEntity(PersonVO person) {
        return PersonDto.builder()
                .DNI(person.getDNI())
                .nombre(person.getNombre())
                .apellidos(person.getApellidos())
                .calle(person.getCalle())
                .cumpleanios(person.getCumpleanios())
                .build();
    }

    public static PersonVO personMapperEntityToDto(PersonDto personDto) {
        return PersonVO.builder()
                .DNI(personDto.getDNI())
                .nombre(personDto.getNombre())
                .apellidos(personDto.getApellidos())
                .calle(personDto.getCalle())
                .cumpleanios(personDto.getCumpleanios())
                .build();
    }


    public static List<PersonDto> personListMapperDtoToEntity(List<PersonVO> personVOList) {
        return personVOList.stream()
                .map(PersonMapper::personMapperDtoToEntity)
                .collect(Collectors.toList());
    }


    public static List<PersonVO> personListMapperEntityToDto(List<PersonDto> personDtoList) {
        return personDtoList.stream()
                .map(PersonMapper::personMapperEntityToDto)
                .collect(Collectors.toList());
    }
}
