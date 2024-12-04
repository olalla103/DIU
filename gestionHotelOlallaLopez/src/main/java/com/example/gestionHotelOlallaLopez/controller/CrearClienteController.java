package com.example.gestionHotelOlallaLopez.controller;

import com.example.gestionHotelOlallaLopez.modelo.Cliente;
import com.example.gestionHotelOlallaLopez.modelo.ReservaModelo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearClienteController {
    @FXML
    private TextField dniField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField direcionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;
    private Stage dialogStage;
    private ReservaModelo reservaModelo;
    private Cliente cliente;
    private boolean okClicked = false;


    /**
     * Un set de la Cliente
     *
     * @param cliente
     */
    public void setcliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getdni() != null) {
            dniField.setText(cliente.getdni());
            dniField.setEditable(false);
            nombreField.setText(cliente.getNombre());
            apellidosField.setText(cliente.getApellidos());
            direcionField.setText(cliente.getDireccion());
            localidadField.setText(cliente.getLocalidad());
            provinciaField.setText(cliente.getProvincia());
        } else {
            dniField.setText(cliente.getdni());
            nombreField.setText(cliente.getNombre());
            apellidosField.setText(cliente.getApellidos());
            direcionField.setText(cliente.getDireccion());
            localidadField.setText(cliente.getLocalidad());
            provinciaField.setText(cliente.getProvincia());
        }

    }

    public void setreservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Boton para aceptar
     *
     * @param actionEvent
     */
    public void handleOk(ActionEvent actionEvent) {
        if (isInputValid()) {
            cliente.setdni(dniField.getText());
            cliente.setNombre(nombreField.getText());
            cliente.setApellidos(apellidosField.getText());
            cliente.setDireccion(direcionField.getText());
            cliente.setLocalidad(localidadField.getText());
            cliente.setProvincia(provinciaField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Boton para cancelar
     *
     * @param actionEvent
     */
    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    /**
     * Devuelve okClickled
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Comprueba que esten bien los campos
     *
     * @return
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (dniField.getText() == null || dniField.getText().length() == 0 || dniField.getText().length() != 9) {
            errorMessage += "DNI incorrecto\n";
        }
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre no válido\n";
        }
        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMessage += "Apellidos no válidos\n";
        }

        if (direcionField.getText() == null || direcionField.getText().length() == 0) {
            errorMessage += "Dirección no válida\n";
        }
        

        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "Localidad no válida\n";
        }

        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "Provincia no válida\n";
        }
//        } else {
//            if (!DateUtil.validDate(birthdayField.getText())) {
//                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
//            }
//        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Invalid Fields");
            alerta.setHeaderText("Please correct invalid fields");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();
            return false;
        }
    }

    /**
     * Boton para vaciar los campos
     *
     * @param actionEvent
     */
    public void handleVaciar(ActionEvent actionEvent) {
        nombreField.setText("");
        apellidosField.setText("");
        direcionField.setText("");
        localidadField.setText("");
        provinciaField.setText("");
    }
}