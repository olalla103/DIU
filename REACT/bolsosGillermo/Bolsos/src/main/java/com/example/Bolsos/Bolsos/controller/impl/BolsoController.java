package com.example.Bolsos.Bolsos.controller.impl;

import com.example.Bolsos.Bolsos.controller.BolsoApi;
import com.example.Bolsos.Bolsos.model.BolsoDto;
import com.example.Bolsos.Bolsos.service.BolsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Olalla/Prueba")
public class BolsoController implements BolsoApi {

    @Autowired
    private BolsoService bolsoService;

    // Obtener todos los bolsos
    @Override
    @GetMapping("/Bolsos")
    public List<BolsoDto> getAllContacts() {
        return bolsoService.getAllContacts();
    }

    // Obtener un bolso por ID
    @Override
    @GetMapping("/Bolsos/{id}")
    public Optional<BolsoDto> getContacts(@PathVariable String id) {
        return bolsoService.getContacts(id);
    }

    // Guardar un bolso
    @Override
    @PostMapping("/Bolsos")
    public BolsoDto save(@RequestBody BolsoDto bolsoDto) {
        return bolsoService.save(bolsoDto);
    }

    // Actualizar un bolso
    @Override
    @PutMapping("/Bolsos/{id}")
    public BolsoDto updateContacts(@RequestBody BolsoDto bolsoDto, @PathVariable String id) {
        bolsoDto.setId(id);
        return bolsoService.updateContacts(bolsoDto, id);
    }

    // Eliminar un bolso por ID
    @Override
    @DeleteMapping("/Bolsos/{id}")
    public ResponseEntity deleteContacts(@PathVariable String id) {
        return bolsoService.deleteContacts(id);
    }

    // Eliminar todos los bolsos
    @Override
    @DeleteMapping("/Bolsoss")
    public ResponseEntity deleteContacts() {
        return bolsoService.deleteContacts();
    }
}
