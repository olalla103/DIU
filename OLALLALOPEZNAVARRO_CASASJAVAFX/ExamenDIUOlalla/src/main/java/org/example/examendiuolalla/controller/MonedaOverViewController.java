package org.example.examendiuolalla.controller;

import Modelo.ExcepcionMoneda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.examendiuolalla.MainApp;
import org.example.examendiuolalla.model.MonedaModelo;
import org.example.examendiuolalla.view.Moneda;

public class MonedaOverViewController {
    private MonedaModelo monedaModelo;

    public void setAMonedaModelo(MonedaModelo monedaModelo) {
        this.monedaModelo = monedaModelo;
    }

    @FXML
    private TableView<Moneda> monedaTable;
    @FXML
    private TableColumn<Moneda, String> nombreMoneda;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label multiplicadorLabel;
    @FXML
    private Label totalLabel;

    @FXML
    private TextField eurosTextField;
    @FXML
    private TextField seleccionadoTextField;
    @FXML
    private TextField totalTextField;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MonedaOverViewController() {
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        nombreMoneda.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        showMonedaDetails(null);

        monedaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMonedaDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        monedaTable.setItems(mainApp.getMonedaData());
    }

    private void showMonedaDetails(Moneda moneda) {
        if (moneda != null) {
            // Fill the labels with info from the person object.
            seleccionadoTextField.setText(String.valueOf(moneda.getMultiplicador()));
        } else {
            // Person is null, remove all the text.
            seleccionadoTextField.setText("");
        }
    }

    @FXML
    private void handleDeleteMoneda() throws ExcepcionMoneda {
        int selectedIndex = monedaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Integer cod = monedaTable.getItems().get(selectedIndex).getCodigo();
            monedaTable.getItems().remove(selectedIndex);
            monedaModelo.deleteMoneda(cod);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No ha seleccionado");
            alert.setHeaderText("No ha seleccionado ninguna moneda");
            alert.setContentText("Por favor seleccione una moneda en la tabla.");
            alert.showAndWait();
        }
    }


    public void handleConversor(ActionEvent actionEvent) {
        try {
            if (Integer.parseInt(eurosTextField.getText()) <= 0) {
                if (Integer.parseInt(eurosTextField.getText()) > 0) {
                    Double total = Double.parseDouble(eurosTextField.getText()) * Double.parseDouble(seleccionadoTextField.getText());
                    totalTextField.setText(total.toString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error con las unidades");
                    alert.setTitle("El numero es incorrecto");
                    alert.setContentText("Las unidades no pueden ser negativas");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Los euros no pueden ser menores o iguales a 0");
                alert.setTitle("Error con la cantidad introducida");
                alert.setContentText("Los euros no pueden ser menores o iguales a 0");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al calcular");
            alert.setTitle("Error con las monedas");
            alert.setContentText("Debe introducir una cantidad de euros y seleccionar la moneda");
            alert.showAndWait();
        }
    }

}
