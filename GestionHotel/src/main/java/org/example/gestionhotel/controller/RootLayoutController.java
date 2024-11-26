package org.example.gestionhotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.view.Cliente;

public class RootLayoutController {
    // Referencias a los elementos del FXML
    @FXML
    private TableView<Cliente> tablaClientes; // Tabla para mostrar los clientes

    @FXML
    private TextField dniCliente;
    @FXML
    private TextField direccionCliente;
    @FXML
    private TextField provinciaCliente;
    @FXML
    private TextField localidadCliente;
    @FXML
    private TextField busquedaPorDNI;

    @FXML
    private Button reservasAsociadasCliente;
    @FXML
    private Button nuevaReserva;
    @FXML
    private Button buscarReserva;

    // Menú de gestión
    @FXML
    private MenuItem menuEstadisticas;
    @FXML
    private MenuItem menuGaleria;

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private MainApp mainApp;
    private ClienteModelo clienteModelo;

    // Métodos de inicialización y control de eventos
    @FXML
    private void initialize() {
        // Aquí puedes inicializar la tabla con datos o configuraciones
    }

    private void cargarClientes() {
        try {
            listaClientes.addAll(clienteModelo.mostrarClientes());
            tablaClientes.setItems(listaClientes);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los clientes", Alert.AlertType.ERROR);
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
