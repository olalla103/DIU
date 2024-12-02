package org.example.gestionhotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.view.Cliente;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.util.ClienteUtil;

import java.io.IOException;

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
    private TextField nombreCliente;
    @FXML
    private TextField apellidosCliente;
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
            nombreCliente.setText(cliente.getNombre().get());
            apellidosCliente.setText(cliente.getApellidos().get());
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

    // CORREGIR EL HANDLE DE NUEVO CLIENTE
    @FXML
    private void handleNuevoCliente() {
        // Crear un nuevo cliente con los valores de los campos de texto
        Cliente nuevoCliente = new Cliente();

        // Rellenamos el cliente con los valores de los campos de texto
        nuevoCliente.setDni(dniCliente.getText());
        nuevoCliente.setNombre(nombreCliente.getText());
        nuevoCliente.setApellidos(apellidosCliente.getText());
        nuevoCliente.setDireccion(direccionCliente.getText());
        nuevoCliente.setLocalidad(localidadCliente.getText());
        nuevoCliente.setProvincia(provinciaCliente.getText());

        // Verificar si los campos no están vacíos
        if (!dniCliente.getText().isEmpty() && !nombreCliente.getText().isEmpty() &&
                !apellidosCliente.getText().isEmpty() && !direccionCliente.getText().isEmpty() &&
                !localidadCliente.getText().isEmpty() && !provinciaCliente.getText().isEmpty()) {

            // Intentar insertar el nuevo cliente
            try {
                clienteModelo.insertarCliente(nuevoCliente);  // Insertamos el cliente
                listaClientes.add(nuevoCliente);  // Añadimos a la lista
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo añadir el cliente", AlertType.ERROR);
                mostrarAlerta("Error", "Error al intentar añadir el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            // Mostrar alerta si falta información
            mostrarAlerta("Error", "Todos los campos deben ser llenados.", AlertType.ERROR);
        }
    }


    @FXML
    private void handleNuevoCliente() {
        // Crear un nuevo Cliente con las propiedades como String simples (no StringProperty)
        Cliente nuevoCliente = new Cliente();

        // Asignar los valores de los campos de texto a las propiedades StringProperty
        nuevoCliente.getDni().set(dniCliente.getText());
        nuevoCliente.getNombre().set(nombreCliente.getText());
        nuevoCliente.getApellidos().set(apellidosCliente.getText());
        nuevoCliente.getDireccion().set(direccionCliente.getText());
        nuevoCliente.getLocalidad().set(localidadCliente.getText());
        nuevoCliente.getProvincia().set(provinciaCliente.getText());

        // Ahora se convierte el cliente en StringProperty en su lugar
        // Los valores del cliente ahora están en formato StringProperty, pero no modificamos la clase Cliente

        boolean okClicked = mainApp.mostrarDialogoEdicionCliente();
        if (okClicked) {
            try {
                clienteModelo.insertarCliente(nuevoCliente);  // Se usa el cliente como está
                listaClientes.add(nuevoCliente);
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo añadir el cliente", AlertType.ERROR);
                mostrarAlerta("Error", "No se pudieron cargar los clientes: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }


        // Verificar si los campos necesarios no están vacíos
        if (nuevoCliente.getDni().get().isEmpty() || nuevoCliente.getNombre().get().isEmpty() || nuevoCliente.getApellidos().get().isEmpty()) {
            mostrarAlerta("Advertencia", "Por favor, complete los campos obligatorios.", AlertType.WARNING);
            return; // No proceder si falta información obligatoria
        }

        try {
            // Insertar el nuevo cliente en la base de datos a través del modelo
            clienteModelo.insertarCliente(nuevoCliente);

            // Añadir a la lista de clientes de la interfaz (esto actualizará la tabla de clientes)
            listaClientes.add(nuevoCliente);

            // Limpiar los campos después de la inserción si es necesario
            dniCliente.clear();
            nombreCliente.clear();
            apellidosCliente.clear();
            direccionCliente.clear();
            localidadCliente.clear();
            provinciaCliente.clear();

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo añadir el cliente", AlertType.ERROR);
            mostrarAlerta("Error", "No se pudieron cargar los clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void handleEditarCliente() {
        // Obtengo el cliente seleccionado
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            // Llama al MainApp para mostrar la pantalla de edición
            boolean okClicked = mainApp.mostrarDialogoEdicionCliente(clienteSeleccionado);

            if (okClicked) {
                try {
                    // Actualiza el cliente en el modelo
                    clienteModelo.modificarCliente(clienteSeleccionado);

                    // Refresca la tabla con los nuevos datos
                    tablaClientes.refresh();
                    mostrarDetallesCliente(clienteSeleccionado);
                } catch (Exception e) {
                    mostrarAlerta("Error", "No se pudo actualizar el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ningún cliente", Alert.AlertType.WARNING);
        }
    }


    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();

    }
}