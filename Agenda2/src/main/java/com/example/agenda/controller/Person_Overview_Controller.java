package com.example.agenda.controller;

import com.example.agenda.MainApp;
import com.example.agenda.model.AgendaModelo;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.model.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.example.agenda.view.Person;

public class Person_Overview_Controller {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label street;
    @FXML
    private Label postalCode;
    @FXML
    private Label city;
    @FXML
    private Label birthday;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */

    // TENGO COMENTADOS TODOS LOS HANDLE
    public Person_Overview_Controller() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */

    // Atributos agendaModelo
    private AgendaModelo agendaModelo; // Agregar AgendaModelo aquí

    public void setAgendaModelo(AgendaModelo agendaModelo) {
        this.agendaModelo = agendaModelo;
        loadPersonData(); // Carga los datos cuando se establece el modelo
    }

    private void loadPersonData() {
        if (agendaModelo != null) {
            // Aquí puedes cargar los datos usando agendaModelo
        }
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
            street.setText(person.getStreet());
            postalCode.setText(Integer.toString(person.getPostalCode()));
            city.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String!
            birthday.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstName.setText("");
            lastName.setText("");
            street.setText("");
            postalCode.setText("");
            city.setText("");
            birthday.setText("");
        }
    }

    /*
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.


     @FXML private void handleNewPerson() {
     PersonVO tempPerson = new PersonVO(); // Crea una nueva instancia de PersonVO
     boolean okClicked = mainApp.showPersonEditDialog(tempPerson); // Suponiendo que tu método de diálogo edita PersonVO
     if (okClicked) {
     agendaModelo.addPerson(tempPerson); // Agrega a través de AgendaModelo
     loadPersonData(); // Recarga los datos
     }
     }

     @FXML private void handleEditPerson() {
     PersonVO selectedPerson = personTable.getSelectionModel().getSelectedItem();
     if (selectedPerson != null) {
     boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
     if (okClicked) {
     agendaModelo.updatePerson(selectedPerson); // Actualiza a través de AgendaModelo
     loadPersonData(); // Recarga los datos
     }
     } else {
     // Nothing selected.
     Alert alert = new Alert(Alert.AlertType.WARNING);
     alert.setTitle("No Selection");
     alert.setHeaderText("No Person Selected");
     alert.setContentText("Please select a person in the table.");
     alert.showAndWait();
     }
     }

     @FXML private void handleDeletePerson() {
     int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
     if (selectedIndex >= 0) {
     PersonVO selectedPerson = personTable.getItems().get(selectedIndex);
     agendaModelo.deletePerson(selectedPerson.getId()); // Elimina a través de AgendaModelo
     loadPersonData(); // Recarga los datos después de la eliminación
     } else {
     Alert alert = new Alert(Alert.AlertType.WARNING);
     alert.setTitle("No Selection");
     alert.setHeaderText("No Person Selected");
     alert.setContentText("Please select a person in the table.");
     alert.showAndWait();
     }
     }  */

}

