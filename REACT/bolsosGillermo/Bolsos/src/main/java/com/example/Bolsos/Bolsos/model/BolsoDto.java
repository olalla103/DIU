package com.example.Bolsos.Bolsos.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class BolsoDto {
    @Id
    private String id;
    private String marca;
    private String nombre;
    private int precio;
    private String estilo;
}
