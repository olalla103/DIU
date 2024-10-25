package com.example.agenda.model;

import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonRepository;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.model.util.PersonUtil;
import com.example.agenda.view.Person;

import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;
    ArrayList<PersonVO> personVOArrayList = new ArrayList<>();
    ArrayList<Person> personArrayList = new ArrayList<>();
    PersonUtil personUtil;

    public AgendaModelo() {
        this.personUtil = new PersonUtil();
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ArrayList<PersonVO> getPersonVOArrayList() {
        return personRepository.ObtenerListaPersonas();
    }

    public void mostrarPersonas() {
        try {
            ArrayList<PersonVO> listaPersonas = personRepository.ObtenerListaPersonas();
        } catch (ExceptionPerson e) {
            System.err.println(e.getMessage());
        }
    }


}
