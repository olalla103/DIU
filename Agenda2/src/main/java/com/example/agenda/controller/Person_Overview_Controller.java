package com.example.agenda.controller;

import com.example.agenda.model.AgendaModelo;

import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.model.util.PersonUtil;
import com.example.agenda.model.util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.agenda.MainApp;
import com.example.agenda.view.Person;

import java.io.IOException;

public class Person_Overview_Controller {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    private Integer id;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private AgendaModelo am;
    private PersonUtil cvp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Person_Overview_Controller() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) throws ExceptionPerson {
        this.mainApp = mainApp;
        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Muestra el detalle de las personas
     *
     * @param person
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Metodo usado para transformar de Person a PersonVO a traves del conversor y crearla en la base de datos
     *
     * @param person
     * @throws ExceptionPerson
     */
    public void CrearPersonAPersonVO(Person person) throws ExceptionPerson {
        cvp = new PersonUtil();
        PersonVO personVO = new PersonVO();
        personVO = cvp.convertirPersonaVO(person);
        am.crearPersonVO(personVO);
        am.incNumeroPersonas();
    }

    /**
     * Metodo usado para editar la persona de Person a PersonVO a traves del conversor y editarla en la base de
     * datos
     *
     * @param person
     * @throws ExceptionPerson
     */
    public void editarPersonAPersonVO(Person person) throws ExceptionPerson {
        cvp = new PersonUtil();
        PersonVO personVO = new PersonVO();
        personVO = cvp.convertirPersonaVO(person);
        am.editarPersonVO(personVO);
    }

    /**
     * Instanciamos la agendaModelo
     *
     * @param am
     */
    public void setModelo(AgendaModelo am) {
        this.am = am;
    }

    /**
     * Cuando se pulsa el boton borrar se ejecuta este metodo que borra en la vista y en la base de datos
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            cvp = new PersonUtil();
            try {
                am.deletePersonVO(cvp.convertirPersonaVO(personTable.getItems().get(selectedIndex)));
                am.decNumeroPersonas();
            } catch (ExceptionPerson e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al eliminar la persona");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para eliminar la persona");
                alert.showAndWait();
            }
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No Selection");
            alerta.setHeaderText("No Person Selected");
            alerta.setContentText("Please select a person in the table.");
            alerta.showAndWait();
        }
    }

    /**
     * Cuando se pulsa new se ejecuta este metodo que abre el personEditDialog y crea la persona en la vista
     * y en la base de datos
     *
     * @throws ExceptionPerson
     */
    @FXML
    private void handleNewPerson() throws ExceptionPerson {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            try {
                tempPerson.setIdentificador(am.lastId() + 1);
                CrearPersonAPersonVO(tempPerson);
                mainApp.getPersonData().add(tempPerson);

            } catch (ExceptionPerson e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir la persona");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para añadir la persona");
                alert.showAndWait();
            }
        }

    }

    /**
     * Cuando se pulsa en edit se abre el personEditDialog y se edita la persona en la vista y en la base de datos
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                try {
                    editarPersonAPersonVO(selectedPerson);
                    showPersonDetails(selectedPerson);
                } catch (ExceptionPerson e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al editar la persona");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para editar la persona");
                    alert.showAndWait();
                }
            }

        } else {
            // Nothing selected.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No Selection");
            alerta.setHeaderText("No Person Selected");
            alerta.setContentText("Please select a person in the table.");
            alerta.showAndWait();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}