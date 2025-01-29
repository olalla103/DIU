package org.example.gestionhotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ClienteModelo;

import java.io.IOException;

public class RootLayoutController {
    // Menú de gestión
    @FXML
    private MenuItem menuEstadisticas;
    @FXML
    private MenuItem menuGaleria;
    @FXML
    private MenuItem reservasMenu; // MenuItem para "Reservas"

    private MainApp mainApp;
    private ClienteModelo clienteModelo;


    // Métodos de inicialización y control de eventos
    public void setClienteModelo(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
    }

    // Inyectar la instancia de MainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleReservasMenu(ActionEvent event) {
        if (mainApp != null) {
            mainApp.showReservaOverview();
        }
    }


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}



