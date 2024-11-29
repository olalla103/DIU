package org.example.gestionhotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.util.ClienteUtil;
import org.example.gestionhotel.view.Cliente;

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
    private void handleReservasMenuAction() {
        mainApp.showReservaOverview(); // Llamar a un método de MainApp para mostrar la vista de reservas
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}



