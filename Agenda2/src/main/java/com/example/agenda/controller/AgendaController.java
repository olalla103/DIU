package com.example.agenda.controller;

import com.example.agenda.model.AgendaModelo;
import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private AgendaModelo agendaModelo; // Agregar el atributo para AgendaModelo
    private ObservableList<PersonVO> personList;

    // Método para inyectar AgendaModelo
    public void setAgendaModelo(AgendaModelo agendaModelo) {
        this.agendaModelo = agendaModelo;
        loadPersonData(); // Cargar datos cuando se establece el modelo
    }

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        personList = FXCollections.observableArrayList(); // Inicializar la lista
        personTable.setItems(personList); // Establecer la lista en la tabla

        // Configurar el evento de selección de la tabla
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void loadPersonData() {
        if (agendaModelo != null) {
            try {
                ArrayList<PersonVO> personas = agendaModelo.getPersonVOArrayList(); // Obtener datos desde AgendaModelo
                personList.setAll(personas); // Establecer la lista de personas en la tabla
            } catch (ExceptionPerson e) {
                // Manejo de errores
                e.printStackTrace();
            }
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
    }

    @FXML
    private void handleEditPerson() {
        // Lógica para editar la persona seleccionada
    }

    @FXML
    private void handleDeletePerson() {
        PersonVO selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                agendaModelo.personRepository.deletePersona(selectedPerson.getId()); // Usa el repositorio de AgendaModelo
                loadPersonData(); // Recargar la lista después de la eliminación
            } catch (ExceptionPerson e) {
                // Manejo de errores
                e.printStackTrace();
            }
        }
    }
}
