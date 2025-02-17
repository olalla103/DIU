package com.example.MicroServicioAgenda.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usuarios")
public class UsuarioDto {
    @Id
    private String usuario;
    private String contrasenia;
    private TipoUsuario rol;
}


