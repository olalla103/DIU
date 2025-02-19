package com.example.Bolsos.Bolsos.service;


import com.example.Bolsos.Bolsos.model.BolsoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BolsoService {
    List<BolsoDto> getAllContacts();
    Optional<BolsoDto> getContacts(String id);
    BolsoDto save(BolsoDto Contact);
    BolsoDto updateContacts(BolsoDto contact, String id);
    ResponseEntity deleteContacts(String id);
    ResponseEntity deleteContacts();
}
