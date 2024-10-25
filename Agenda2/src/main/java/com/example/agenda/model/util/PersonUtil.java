package com.example.agenda.model.util;

import com.example.agenda.model.repository.PersonRepository;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.view.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PersonUtil {
    ArrayList<PersonVO> personRepositoryArrayList = new ArrayList<>();
    ArrayList<Person> personArrayList = new ArrayList<>();
    ObjectProperty<Date> dateProperty = new SimpleObjectProperty<>();

    /*que transforme de lista de personVO a lista de person y otro al revés*/

    public ArrayList<Person> convierteVOAPerson() {
        for (PersonVO personVO : personRepositoryArrayList) {
            personArrayList.add(new Person(personVO.getfirstName(), personVO.getlastName(), personVO.getstreet(), personVO.getcity(), personVO.getpostalCode(), personVO.getbirthday()));
        }
        return personArrayList;
    }


}
