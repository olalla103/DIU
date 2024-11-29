package org.example.gestionhotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.view.Cliente;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.util.ClienteUtil;

public class ClienteOverviewController {
    private ClienteModelo clienteModelo;
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
    private CheckBox fumador;
    @FXML
    private Button buscarReserva;

    // Menú de gestión
    @FXML
    private MenuItem menuEstadisticas;
    @FXML
    private MenuItem menuGaleria;

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private MainApp mainApp;

    public ClienteOverviewController() {
    }

    public void setClienteModelo(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
        cargarClientes();
    }

    // método initialize
    // Métodos de inicialización y control de eventos
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        // Clear person details
        mostrarDetallesCliente(null);
        // Listen for selection changes and show the person details when changed.
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesCliente(newValue));
        cargarClientes(); // Cargar los datos al inicializar
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        tablaClientes.setItems(mainApp.getClienteData()); // corregir
    }

    private void cargarClientes() {
        try {
            listaClientes.addAll(clienteModelo.mostrarClientes());
            tablaClientes.setItems(listaClientes);
            listaClientes.clear(); // Limpiar la lista para evitar duplicados
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl(); // Instancia del repositorio
            listaClientes.addAll(ClienteUtil.getCliente(clienteRepository.ObtenerListaClientes()));
            tablaClientes.setItems(listaClientes); // Vincular los datos al TableView
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los clientes", AlertType.ERROR);
        }
    }

    private void mostrarDetallesCliente(Cliente cliente) {
        if (cliente != null) {
            dniCliente.setText(cliente.getDni().get());
            direccionCliente.setText(cliente.getDireccion().get());
            localidadCliente.setText(cliente.getLocalidad().get());
            provinciaCliente.setText(cliente.getProvincia().get());
        } else {
            dniCliente.setText("");
            direccionCliente.setText("");
            localidadCliente.setText("");
            provinciaCliente.setText("");
        }
    }

    @FXML
    private void handleEliminarCliente() {
        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente clienteSeleccionado = tablaClientes.getItems().get(selectedIndex);
            try {
                clienteModelo.eliminarCliente(clienteSeleccionado.getDni().get());
                tablaClientes.getItems().remove(selectedIndex);
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el cliente", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ningún cliente", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleNuevoCliente() {
        Cliente nuevoCliente = new Cliente();
        boolean okClicked = mostrarDialogoEdicionCliente(nuevoCliente);
        if (okClicked) {
            try {
                clienteModelo.insertarCliente(nuevoCliente);
                listaClientes.add(nuevoCliente);
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo añadir el cliente", AlertType.ERROR);
                mostrarAlerta("Error", "No se pudieron cargar los clientes: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }


    @FXML
    private boolean handleEditarCliente() {
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            boolean okClicked = mostrarDialogoEdicionCliente(clienteSeleccionado);
            if (okClicked) {
                try {
                    clienteModelo.modificarCliente(clienteSeleccionado);
                    mostrarDetallesCliente(clienteSeleccionado);
                } catch (Exception e) {
                    mostrarAlerta("Error", "No se pudo editar el cliente", AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ningún cliente", AlertType.WARNING);
        }

        return false;
    }

    private boolean mostrarDialogoEdicionCliente(Cliente cliente) {
        // Aquí deberías implementar la lógica para mostrar un diálogo de edición
        // Puedes usar un nuevo controlador y una vista adicional en FXML.
        return true; // Cambia según tu implementación del diálogo
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();

    }
}