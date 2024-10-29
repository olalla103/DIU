package com.example.agenda.controller;

import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonRepository;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.model.repository.impl.PersonRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.util.ArrayList;

public class AgendaController {
    @FXML
    private TableView<PersonVO> personTable;
    @FXML
    private TableColumn<PersonVO, String> firstNameColumn;
    @FXML
    private TableColumn<PersonVO, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Button newPersonButton;
    @FXML
    private Button editPersonButton;
    @FXML
    private Button deletePersonButton;

    private PersonRepository personRepository;
    private ObservableList<PersonVO> personList;

    public AgendaController() {
        this.personRepository = new PersonRepositoryImpl();
        this.personList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        // Cargar la lista de personas
        loadPersonData();

        // Configurar el evento de selección de la tabla
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void loadPersonData() {
        try {
            ArrayList<PersonVO> personas = personRepository.ObtenerListaPersonas();
            personList.setAll(personas);
            personTable.setItems(personList);
        } catch (ExceptionPerson e) {
            // Manejo de errores (puedes mostrar un mensaje al usuario)
            e.printStackTrace();
        }
    }

    private void showPersonDetails(PersonVO person) {
        if (person != null) {
            firstNameLabel.setText(person.getfirstName());
            lastNameLabel.setText(person.getlastName());
            streetLabel.setText(person.getstreet());
            cityLabel.setText(person.getcity());
            postalCodeLabel.setText(String.valueOf(person.getpostalCode()));
            birthdayLabel.setText(person.getbirthday().toString());
        } else {
            // Limpiar los detalles si no hay selección
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleNewPerson() {
        // Lógica para añadir una nueva persona
        // Podrías abrir un diálogo para ingresar nuevos datos
    }

    @FXML
    private void handleEditPerson() {
        // Lógica para editar la persona seleccionada
        // Podrías abrir un diálogo para modificar los datos
    }

    @FXML
    private void handleDeletePerson() {
        PersonVO selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                personRepository.deletePersona(selectedPerson.getId());
                loadPersonData(); // Recargar la lista después de la eliminación
            } catch (ExceptionPerson e) {
                // Manejo de errores
                e.printStackTrace();
            }
        }
    }
}
