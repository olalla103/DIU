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
    // Referencias a los elementos del FXML
    @FXML
    private TableView<Cliente> tablaClientes; // Tabla para mostrar los clientes

    @FXML
    private TableColumn<Cliente, String> columnaNombre; // Define las columnas
    @FXML
    private TableColumn<Cliente, String> columnaApellidos;

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
        // Configurar las columnas para que usen las propiedades del objeto Cliente
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        cargarClientes(); // Cargar los datos al inicializar
    }

    private void cargarClientes() {
        try {
            listaClientes.clear(); // Limpiar la lista para evitar duplicados
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl(); // Instancia del repositorio
            listaClientes.addAll(ClienteUtil.getCliente(clienteRepository.ObtenerListaClientes()));
            tablaClientes.setItems(listaClientes); // Vincular los datos al TableView
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void setClienteModelo(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
    }



    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}



