package com.example.agenda.model.util;

import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.view.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.Date;

public class PersonUtil {
    public Person convertirPersona(PersonVO personVO){
        Person person = new Person();
        person.setIdentificador(personVO.getId());
        person.setFirstName(personVO.getNombre());
        person.setLastName(personVO.getApellido());
        person.setStreet(personVO.getCalle());
        person.setPostalCode(personVO.getCodigoPostal());
        person.setCity(personVO.getCiudad());
        person.setBirthday(personVO.getNacimiento());
        return person;
    }

    /**
     * Lista se le pasa un arrayList de PersonVO y devuelve un arrayList de Person.
     * @param listaPersonVO
     * @return listaPerson -Lista de Person
     */
    public ArrayList<Person> lista(ArrayList<PersonVO> listaPersonVO){
        ArrayList<Person> listaPerson = new ArrayList<Person>();
        Person person = new Person();
        for(int i=0;i<listaPersonVO.size();i++){
            person=convertirPersona(listaPersonVO.get(i));
            listaPerson.add(i,person);
        }
        return listaPerson;
    }

    /**
     * Convierte una Person a PersonVO
     * @param person
     * @return PersonVO
     */
    public PersonVO convertirPersonaVO(Person person){
        PersonVO personVO = new PersonVO();
        personVO.setId(person.getIdentificador());
        personVO.setNombre(person.getFirstName());
        personVO.setApellido(person.getLastName());
        personVO.setCalle(person.getStreet());
        personVO.setCodigoPostal(person.getPostalCode());
        personVO.setCiudad(person.getCity());
        personVO.setNacimiento(person.getBirthday());
        return personVO;
    }

    /**
     * Convierte una lista Person a lista de PersonVO.
     * @param listaPerson
     * @return listaPersonVO
     */
    public ArrayList<PersonVO> listaPerson(ArrayList<Person> listaPerson){
        ArrayList<PersonVO> listaPersonVO = new ArrayList<PersonVO>();
        PersonVO personVO = new PersonVO();
        for(int i=0;i<listaPerson.size();i++){
            personVO=convertirPersonaVO(listaPerson.get(i));
            listaPersonVO.add(i,personVO);
        }
        return listaPersonVO;
    }

}
