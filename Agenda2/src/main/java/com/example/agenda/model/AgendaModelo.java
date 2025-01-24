package com.example.agenda.model;

import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonRepository;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.model.util.PersonUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;


    IntegerProperty numeroPersonas = new SimpleIntegerProperty();

    public void setNumeroPersonas(Integer nP) {
        this.numeroPersonas.set(nP);
    }

    public void decNumeroPersonas() {
        this.numeroPersonas.set(numeroPersonas.get() - 1);
    }

    public void incNumeroPersonas() {
        this.numeroPersonas.set(numeroPersonas.get() + 1);
    }

    public IntegerProperty numeroPersonasProperty() {
        return numeroPersonas;
    }

    /**
     * Constructor vacio
     */
    public AgendaModelo() {

    }

    /**
     * Lista las personas de la base de datos a partir de la interfaz
     *
     * @return lista de persona de la base de datos
     * @throws ExceptionPerson
     */
    public ArrayList<PersonVO> listarPersonas() throws ExceptionPerson {
        return personRepository.ObtenerListaPersonas();
    }

    /**
     * Crea la persona en la base de datos a partir de la interfaz
     *
     * @param personVO
     * @throws ExceptionPerson
     */
    public void crearPersonVO(PersonVO personVO) throws ExceptionPerson {
        personRepository.addPerson(personVO);
    }

    /**
     * Edita la persona en la base de daros a partir de la interfaz.
     *
     * @param personVO
     * @throws ExceptionPerson
     */
    public void editarPersonVO(PersonVO personVO) throws ExceptionPerson {
        personRepository.editPerson(personVO);
    }

    /**
     * Borra la persona de la base de datos a partir de la interfaz
     *
     * @param personVO
     * @throws ExceptionPerson
     */
    public void deletePersonVO(PersonVO personVO) throws ExceptionPerson {
        personRepository.deletePerson(personVO.getId());
    }

    /**
     * Obtiene el id de la ultima persona de la base de datos.
     *
     * @return id
     * @throws ExceptionPerson
     */
    public int lastId() throws ExceptionPerson {
        return personRepository.lastId();
    }

    /**
     * Instanciamos el interfaz
     *
     * @param inter
     */
    public void setImpl(PersonRepository inter) {
        this.personRepository = inter;
    }
}