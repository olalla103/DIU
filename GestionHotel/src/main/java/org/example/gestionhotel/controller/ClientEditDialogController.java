package org.example.gestionhotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.ClienteVO;
import org.example.gestionhotel.view.Cliente;

import java.util.ArrayList;

public class ClientEditDialogController {

    public ClientEditDialogController() {
    }

    // campos a editar
    @FXML
    private TextField dniCampo;
    @FXML
    private TextField nombreCampo;
    @FXML
    private TextField apellidosCampo;
    @FXML
    private TextField direccionCampo;
    @FXML
    private TextField localidadCampo;
    @FXML
    private TextField provinciaCampo;


    // Barra de progreso
    /*@FXML
    private ProgressBar progreso;
    @FXML
    private ProgressIndicator indicator;*/

    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ArrayList<ClienteVO> clientes = ClienteModelo.obtenerClientes();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        // Usa .get() para obtener el valor del StringProperty
        dniCampo.setText(cliente.getDni().get());
        if (cliente.getDni().get() != null) {
            dniCampo.setDisable(true);
        }
        nombreCampo.setText(cliente.getNombre().get());
        apellidosCampo.setText(cliente.getApellidos().get());
        direccionCampo.setText(cliente.getDireccion().get());
        localidadCampo.setText(cliente.getLocalidad().get());
        provinciaCampo.setText(cliente.getProvincia().get());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleBien() {
        if (isInputValid()) {
            cliente.getDni().set(dniCampo.getText());
            cliente.getNombre().set(nombreCampo.getText());
            cliente.getApellidos().set(apellidosCampo.getText());
            cliente.getDireccion().set(direccionCampo.getText());
            cliente.getLocalidad().set(localidadCampo.getText());
            cliente.getProvincia().set(provinciaCampo.getText());

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancelar() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (dniCampo.getText() == null || "".equals(dniCampo.getText())) {
            errorMessage += "Dni no valido\n";
        }
        if (nombreCampo.getText() == null || nombreCampo.getText().isEmpty()) {
            errorMessage += "Nombre no valido\n";
        }
        if (apellidosCampo.getText() == null || apellidosCampo.getText().isEmpty()) {
            errorMessage += "Apellidos no validos\n";
        }
        if (direccionCampo.getText() == null || direccionCampo.getText().isEmpty()) {
            errorMessage += "Direccion no valida\n";
        }

        if (localidadCampo.getText() == null || localidadCampo.getText().isEmpty()) {
            errorMessage += "localidad no valida\n";
        }

        if (provinciaCampo.getText() == null || provinciaCampo.getText().isEmpty()) {
            errorMessage += "Provincia no valida\n";
        }

        /* if (provinciaCampo.getText() == null || provinciaCampo.getText().length() == 0) {
            errorMessage += "Provincia no valida\n";
        }*/

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo no rellenado");
            alert.setHeaderText("Campo no rellenado");
            alert.setContentText("Debe rellenar todos los campos");

            alert.showAndWait();
            return false;
        }
    }


}