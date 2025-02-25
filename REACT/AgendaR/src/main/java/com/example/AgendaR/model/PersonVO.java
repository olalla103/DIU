package com.example.AgendaR.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
public class PersonVO {
    private String DNI;
    private String nombre;
    private String apellidos;
    private String calle;
    private Integer codigoPostal;
    private String ciudad;
    private LocalDate cumpleanios;
    private List<String> tutorialsIds; // Lista de IDs de tutoriales
}
