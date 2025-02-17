package com.example.MicroServicioAgenda.service;

import com.example.MicroServicioAgenda.model.UsuarioDto;
import com.example.MicroServicioAgenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Usa PasswordEncoder en vez de BCryptPasswordEncoder

    public UsuarioDto registerUsuario(UsuarioDto usuario) {
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia())); // Encripta la contraseña
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioDto> authenticateUsuario(String username, String password) {
        Optional<UsuarioDto> usuario = usuarioRepository.findByUsuario(username);
        if (usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getContrasenia())) {
            return usuario;
        }
        return Optional.empty();
    }

    public List<UsuarioDto> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

}
