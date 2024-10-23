package com.example.agenda.model;

import com.example.agenda.model.repository.PersonRepository;

public class AgendaModelo {
    PersonRepository personRepository;

    public AgendaModelo(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

}
