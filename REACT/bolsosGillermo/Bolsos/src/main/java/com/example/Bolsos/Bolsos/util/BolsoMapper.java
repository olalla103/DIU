package com.example.Bolsos.Bolsos.util;


import com.example.Bolsos.Bolsos.model.Bolso;
import com.example.Bolsos.Bolsos.model.BolsoDto;

import java.util.List;
import java.util.stream.Collectors;

public class BolsoMapper {

    // Convierte un DTO a una entidad
    public static Bolso agendaMapperDtoToEntity(BolsoDto agendaDto) {
        return Bolso.builder()
                .id(agendaDto.getId())
                .marca(agendaDto.getMarca())
                .precio(agendaDto.getPrecio())
                .estilo(agendaDto.getEstilo())
                .build();
    }

    // Convierte una entidad a un DTO
    public static BolsoDto agendaMapperEntityToDto(Bolso bolso) {
        return BolsoDto.builder()
                .id(bolso.getId()) // Asigna el ID desde la entidad
                .marca(bolso.getMarca()) // Asigna la marca desde la entidad
                .precio(bolso.getPrecio()) // Asigna el precio desde la entidad
                .estilo(bolso.getEstilo()) // Asigna el estilo desde la entidad
                .build(); // Devuelve el objeto BolsoDto construido
    }

    // Convierte una lista de DTOs a una lista de entidades
    public static List<Bolso> agendaListMapperDtoToEntity(List<BolsoDto> agendaDtoList) {
        return agendaDtoList.stream()
                .map(BolsoMapper::agendaMapperDtoToEntity) // Mapea cada DTO a entidad
                .collect(Collectors.toList()); // Recoge las entidades en una lista
    }

    // Convierte una lista de entidades a una lista de DTOs
    public static List<BolsoDto> agendaListMapperEntityToDto(List<Bolso> agendaList) {
        return agendaList.stream()
                .map(BolsoMapper::agendaMapperEntityToDto) // Mapea cada entidad a DTO
                .collect(Collectors.toList()); // Recoge los DTOs en una lista
    }
}
