package com.example.gestionHotelOlallaLopez.controller;

import com.example.gestionHotelOlallaLopez.modelo.*;
import com.example.gestionHotelOlallaLopez.modelo.ReservaModelo;
import com.example.gestionHotelOlallaLopez.util.ClaseUtilidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class BusquedaClienteController {
    @FXML
    private TextField DNIbusq;
    @FXML
    private TableView<Cliente> tablaCliente;
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Cliente, String> dniC;
    @FXML
    private TableColumn<Cliente, String> nombreC;
    @FXML
    private TableColumn<Cliente, String> apellidosC;
    @FXML
    private TableColumn<Cliente, String> direcionC;
    @FXML
    private TableColumn<Cliente, String> localidadC;
    @FXML
    private TableColumn<Cliente, String> provinciaC;
    @FXML
    private TableView<Reserva> tablaReserva;
    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reserva, Integer> idC;
    @FXML
    private TableColumn<Reserva, LocalDate> fechaEntradaC;
    @FXML
    private TableColumn<Reserva, LocalDate> fechaFinC;
    @FXML
    private TableColumn<Reserva, Integer> numHabC;
    @FXML
    private TableColumn<Reserva, String> tipHabC;
    @FXML
    private TableColumn<Reserva, Boolean> fumC;
    @FXML
    private TableColumn<Reserva, String> regAlojC;
    private Stage dialogStage;
    @FXML
    private Button lupa;
    private ClaseUtilidad claseUtilidad;
    private ReservaModelo reservaModelo;

    public ClaseUtilidad getClaseUtilidad() {
        return claseUtilidad;
    }

    public void setClaseUtilidad(ClaseUtilidad claseUtilidad) {
        this.claseUtilidad = claseUtilidad;
    }

    public ReservaModelo getreservaModelo() {
        return reservaModelo;
    }

    public void setReservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Boton para cerrar
     *
     * @param actionEvent
     */
    public void handleCerrar(ActionEvent actionEvent) {
        dialogStage.close();
    }

    /**
     * Boton para vaciar el campo dni
     *
     * @param actionEvent
     */
    public void handleVaciar(ActionEvent actionEvent) {
        DNIbusq.setText("");
    }

    /**
     * Boton para buscar Cliente por dni
     */
    public void handleBuscar() {
        try {
            ClienteVO clienteVO = new ClienteVO();
            clienteVO.setDNIVO(DNIbusq.getText());
            Cliente cliente = new Cliente();
            cliente = claseUtilidad.convertirCliente(reservaModelo.busquedaClienteDNI(clienteVO));
            listaClientes.clear();
            if (cliente != null) {
                listaClientes.add(cliente);
            }
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error con la lista del cliente");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos para buscar el cliente o no existe el cliente");
            alert.showAndWait();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al buscar la Cliente");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos para buscar el cliente o no existe el cliente");
            alert.showAndWait();
        }
        dniC.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        nombreC.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidosC.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        direcionC.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        localidadC.setCellValueFactory(cellData -> cellData.getValue().localidadProperty());
        provinciaC.setCellValueFactory(cellData -> cellData.getValue().provinciaProperty());
        tablaCliente.setItems(listaClientes);
        try {
            ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
            ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
            listaReservaVO = reservaModelo.listarReservas(DNIbusq.getText());
            listaReserva = claseUtilidad.listaReserva(listaReservaVO);
            listaReservas.clear();
            listaReservas.addAll(listaReserva);
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al buscar las reservas");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos para buscar las reservas");
            alert.showAndWait();
        }
        idC.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fechaEntradaC.setCellValueFactory(cellData -> cellData.getValue().fechaLlegadaProperty());
        fechaFinC.setCellValueFactory(cellData -> cellData.getValue().fechaFinProperty());
        numHabC.setCellValueFactory(cellData -> cellData.getValue().numHabitacionProperty().asObject());
        tipHabC.setCellValueFactory(cellData -> cellData.getValue().tipoHabitacionProperty());
        fumC.setCellValueFactory(cellData -> cellData.getValue().fumadorProperty());
        regAlojC.setCellValueFactory(cellData -> cellData.getValue().regimenAlojamientoProperty());
        tablaReserva.setItems(listaReservas);
    }

    @FXML
    public void initialize() {
        DNIbusq.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleBuscar();
                }
            }
        });
    }


}
