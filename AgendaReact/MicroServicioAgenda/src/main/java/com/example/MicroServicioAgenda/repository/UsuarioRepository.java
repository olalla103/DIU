package com.example.MicroServicioAgenda.repository;

import com.example.MicroServicioAgenda.model.UsuarioDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<UsuarioDto, String> {
    Optional<UsuarioDto> findByUsuario(String usuario);
}
