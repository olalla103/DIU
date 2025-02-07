package com.example.MicroServicioAgenda.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class PersonDto {
    @Id
    private String DNI;
    private String nombre;
    private String apellidos;
    private String calle;
    private Integer codigoPostal;
    private String ciudad;
    private LocalDate cumpleanios;
}
