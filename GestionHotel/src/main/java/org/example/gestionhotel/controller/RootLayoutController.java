package org.example.gestionhotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.util.ClienteUtil;
import org.example.gestionhotel.view.Cliente;

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


    public void handleReservasMenu(ActionEvent event) {
        try {
            // Cargar el archivo FXML de ReservaOverview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/gestionhotel/view/ReservaOverview.fxml"));
            Scene scene = new Scene(loader.load());

            // Crear la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Vista de Reservas");
            stage.setScene(scene);

            // Mostrar la ventana
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores si no se pudo cargar el archivo FXML
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



