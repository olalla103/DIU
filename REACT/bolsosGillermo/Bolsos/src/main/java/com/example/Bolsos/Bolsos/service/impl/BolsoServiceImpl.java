package com.example.Bolsos.Bolsos.service.impl;

import com.example.Bolsos.Bolsos.model.BolsoDto;
import com.example.Bolsos.Bolsos.model.Bolso;
import com.example.Bolsos.Bolsos.repository.BolsoRepository;
import com.example.Bolsos.Bolsos.service.BolsoService;
import com.example.Bolsos.Bolsos.util.BolsoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BolsoServiceImpl implements BolsoService {

    @Autowired
    private BolsoRepository bolsoRepository;

    @Override
    public List<BolsoDto> getAllContacts() {
        List<Bolso> bolsoList = bolsoRepository.findAll();
        return bolsoList.stream()
                .map(BolsoMapper::agendaMapperEntityToDto) // Cambiado de agendaMapper a bolsoMapper
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BolsoDto> getContacts(String id) {
        Optional<Bolso> bolsoOptional = bolsoRepository.findById(id);
        return bolsoOptional.map(BolsoMapper::agendaMapperEntityToDto); // Cambiado de agendaMapper a bolsoMapper
    }

    @Override
    public BolsoDto save(BolsoDto bolsoDto) {
        Bolso bolso = BolsoMapper.agendaMapperDtoToEntity(bolsoDto); // Cambiado de agendaMapper a bolsoMapper
        Bolso savedBolsoEntity = bolsoRepository.save(bolso);
        return BolsoMapper.agendaMapperEntityToDto(savedBolsoEntity); // Cambiado de agendaMapper a bolsoMapper
    }

    @Override
    public BolsoDto updateContacts(BolsoDto bolsoDto, String id) {
        Optional<Bolso> existingBolsoOptional = bolsoRepository.findById(id);

        if (existingBolsoOptional.isPresent()) {
            Bolso existingBolso = existingBolsoOptional.get();
            existingBolso.setMarca(bolsoDto.getMarca()); // Ajuste de atributo
            existingBolso.setId(bolsoDto.getId()); // Ajuste de atributo
            existingBolso.setPrecio(bolsoDto.getPrecio()); // Ajuste de atributo
            existingBolso.setEstilo(bolsoDto.getEstilo()); // Ajuste de atributo

            Bolso updatedBolso = bolsoRepository.save(existingBolso);
            return BolsoMapper.agendaMapperEntityToDto(updatedBolso); // Cambiado de agendaMapper a bolsoMapper
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deleteContacts(String id) {
        try {
            Optional<Bolso> existingBolsoOptional = bolsoRepository.findById(id);
            if (existingBolsoOptional.isPresent()) {
                bolsoRepository.deleteById(id);
                return ResponseEntity.ok("Bolso eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bolso no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el bolso");
        }
    }

    @Override
    public ResponseEntity deleteContacts() {
        bolsoRepository.deleteAll();
        return ResponseEntity.ok().body("Todos los bolsos eliminados exitosamente");
    }
}
