package com.example.Bolsos.Bolsos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class Bolso {
    @Id
    private String id;
    private String marca;
    private String nombre;
    private int precio;
    private String estilo;

}

