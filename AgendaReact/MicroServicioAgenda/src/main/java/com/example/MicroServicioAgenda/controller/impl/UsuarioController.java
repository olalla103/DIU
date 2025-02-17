package com.example.MicroServicioAgenda.controller;

import com.example.MicroServicioAgenda.model.UsuarioDto;
import com.example.MicroServicioAgenda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public UsuarioDto register(@RequestBody UsuarioDto usuario) {
        return usuarioService.registerUsuario(usuario);
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDto usuario) {
        Optional<UsuarioDto> authenticatedUsuario = usuarioService.authenticateUsuario(usuario.getUsuario(), usuario.getContrasenia());
        return authenticatedUsuario.isPresent() ? "Login exitoso" : "Credenciales incorrectas";
    }

    @GetMapping("/usuarios")
    public List<UsuarioDto> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

}
