package org.example.gestionhotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.view.Cliente;
import org.w3c.dom.Text;

public class ClienteOverviewController {

    private ClienteModelo clienteModelo;

    @FXML
    private TableView<Cliente> tablaCliente;

    @FXML
    private TableColumn<Cliente, String> nombreColumna;

    @FXML
    private TableColumn<Cliente, String> apellidosColumna;

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

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    public ClienteOverviewController() {
    }

    public void setClienteModelo(ClienteModelo clienteModelo) {
        this.clienteModelo = clienteModelo;
        cargarClientes();
    }

    @FXML
    private void initialize() {
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        apellidosColumna.setCellValueFactory(cellData -> cellData.getValue().getApellidos());

        mostrarDetallesCliente(null);

        tablaCliente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesCliente(newValue)
        );
    }

    private void cargarClientes() {
        try {
            listaClientes.addAll(clienteModelo.mostrarClientes());
            tablaCliente.setItems(listaClientes);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los clientes", AlertType.ERROR);
        }
    }

    private void mostrarDetallesCliente(Cliente cliente) {
        if (cliente != null) {
            dniCampo.setText(cliente.getDni().get());
            nombreCampo.setText(cliente.getNombre().get());
            apellidosCampo.setText(cliente.getApellidos().get());
            direccionCampo.setText(cliente.getDireccion().get());
            localidadCampo.setText(cliente.getLocalidad().get());
            provinciaCampo.setText(cliente.getProvincia().get());
        } else {
            dniCampo.setText("");
            nombreCampo.setText("");
            apellidosCampo.setText("");
            direccionCampo.setText("");
            localidadCampo.setText("");
            provinciaCampo.setText("");
        }
    }


    @FXML
    private void handleEliminarCliente() {
        int selectedIndex = tablaCliente.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente clienteSeleccionado = tablaCliente.getItems().get(selectedIndex);
            try {
                clienteModelo.eliminarCliente(clienteSeleccionado.getDni().get());
                tablaCliente.getItems().remove(selectedIndex);
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el cliente", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ningún cliente", AlertType.WARNING);
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
            }
        }
    }

    @FXML
    private void handleEditarCliente() {
        Cliente clienteSeleccionado = tablaCliente.getSelectionModel().getSelectedItem();
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
