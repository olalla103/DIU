package com.example.gestionHotelOlallaLopez.controller;

import com.example.gestionHotelOlallaLopez.MainApp;
import com.example.gestionHotelOlallaLopez.modelo.*;
import com.example.gestionHotelOlallaLopez.util.ClaseUtilidad;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.io.IOException;

public class RootLayoutController {

    private MainApp mainApp;
    private ReservaModelo reservaModelo;
    private ClaseUtilidad claseUtilidad;
    private TableView<Cliente> tablaCliente;

    private TableView<Reserva> tablaReserva;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setreservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;
    }

    public void setclaseUtilidad(ClaseUtilidad claseUtilidad) {
        this.claseUtilidad = claseUtilidad;
    }

    /**
     * Opcion del menu para añadir Cliente
     *
     * @param actionEvent
     */
    public void handleAddCliente(ActionEvent actionEvent) {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showclienteEditDialog(tempCliente);
        if (okClicked) {
            try {
                ClienteVO ClienteVO = new ClienteVO();
                ClienteVO = claseUtilidad.convertirClienteVO(tempCliente);
                reservaModelo.crearClienteVO(ClienteVO);
                mainApp.getclienteData().add(tempCliente);
            } catch (ExcepcionHotel e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir la Cliente");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para añadir la Cliente");
                alert.showAndWait();
            }
        }
    }

    /**
     * Opcion del menu para editar la Cliente
     *
     * @param actionEvent
     */
    public void handleEditCliente(ActionEvent actionEvent) {
        tablaCliente = mainApp.getTablaCliente();
        Cliente selectedCliente = tablaCliente.getItems().get(mainApp.getI());
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showclienteEditDialog(selectedCliente);
            if (okClicked) {
                try {
                    ClienteVO ClienteVO = new ClienteVO();
                    ClienteVO = claseUtilidad.convertirClienteVO(selectedCliente);
                    reservaModelo.editarClienteVO(ClienteVO);
                } catch (ExcepcionHotel e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al editar la Cliente");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para editar la Cliente");
                    alert.showAndWait();
                }
            }

        } else {
            // Nothing selected.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No Selection");
            alerta.setHeaderText("No Cliente Selected");
            alerta.setContentText("Please select a Cliente in the table.");
            alerta.showAndWait();
        }
    }

    /**
     * Opcion del menu para borrar Cliente
     *
     * @param actionEvent
     */
    public void handleDeleteCliente(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("No disponible");
        alerta.setHeaderText("Esta opcion no esta disponible");
        alerta.setContentText("El borrado de Clientes no esta disponible en este momento.");
        alerta.showAndWait();
    }

    /**
     * Opcion del menu para añadir reserva
     *
     * @param actionEvent
     */
    public void handleAddReserva(ActionEvent actionEvent) {
        tablaCliente = mainApp.getTablaCliente();
        try {
            Cliente selectedCliente = tablaCliente.getItems().get(mainApp.getI());
            Reserva tempReserva = new Reserva();
            boolean okClicked = mainApp.showReservaEditDialog(tempReserva, selectedCliente);
            if (okClicked) {
                try {
                    tempReserva.setId(reservaModelo.lastIdReserva() + 1);
                    ReservaVO reservaVO = new ReservaVO();
                    reservaVO = claseUtilidad.convertirReservaVO(tempReserva);
                    reservaModelo.crearReservaVO(reservaVO);
                    mainApp.getReservaData().add(tempReserva);
                    if (tempReserva.gettipoHabitacion().equals("Doble de uso individual")) {
                        reservaModelo.incNumeroClientes();
                    } else if (tempReserva.gettipoHabitacion().equals("Doble")) {
                        reservaModelo.incNumeroClientes2();
                    } else if (tempReserva.gettipoHabitacion().equals("Junior suite")) {
                        reservaModelo.incNumeroClientes3();
                    } else if (tempReserva.gettipoHabitacion().equals("Suite")) {
                        reservaModelo.incNumeroClientes4();
                    }
                } catch (ExcepcionHotel e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al añadir la reserva");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para añadir la reserva");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error: seleccione una Cliente");
            alert.setTitle("Error al mostrar la reserva");
            alert.setContentText("Tiene que seleccionar la Cliente para mostrar la reserva");
            alert.showAndWait();
        }

    }

    /**
     * Opcion del menu para editar la reserva
     *
     * @param actionEvent
     */
    public void handleEditReserva(ActionEvent actionEvent) {
        tablaCliente = mainApp.getTablaCliente();
        if (mainApp.getI() != null) {
            Cliente selectedCliente = tablaCliente.getItems().get(mainApp.getI());
            tablaReserva = mainApp.getTablaReserva();
            if (mainApp.getiR() != null) {
                Reserva selectedReserva = tablaReserva.getItems().get(mainApp.getiR());
                boolean okClicked = mainApp.showReservaEditDialog(selectedReserva, selectedCliente);
                if (okClicked) {
                    try {
                        ReservaVO reservaVO = new ReservaVO();
                        reservaVO = claseUtilidad.convertirReservaVO(selectedReserva);
                        reservaModelo.editarReservaVO(reservaVO);
                    } catch (ExcepcionHotel e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error al añadir la reserva");
                        alert.setTitle("Error con la base de datos");
                        alert.setContentText("No se puede conectar con la base de datos para añadir la reserva");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("No seleccionado");
                alerta.setHeaderText("Reserva no seleccionada");
                alerta.setContentText("Selecciona una reserva.");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No seleccionado");
            alerta.setHeaderText("Cliente no seleccionada");
            alerta.setContentText("Selecciona una Cliente.");
            alerta.showAndWait();
        }
    }

    /**
     * Opcion del menu para borrar la reserva
     *
     * @param actionEvent
     */
    public void handleDeleteReserva(ActionEvent actionEvent) {
        if (mainApp.getiR() != null) {
            int selectedIndex = mainApp.getiR();
            tablaReserva = mainApp.getTablaReserva();
            if (selectedIndex >= 0) {
                try {
                    reservaModelo.deleteReservaVO(claseUtilidad.convertirReservaVO((tablaReserva.getItems().get(selectedIndex))));
                    if (tablaReserva.getItems().get(selectedIndex).gettipoHabitacion().equals("Doble de uso individual")) {
                        reservaModelo.decNumeroReservas();
                    } else if (tablaReserva.getItems().get(selectedIndex).gettipoHabitacion().equals("Doble")) {
                        reservaModelo.decNumeroReservas2();
                    } else if (tablaReserva.getItems().get(selectedIndex).gettipoHabitacion().equals("Junior suite")) {
                        reservaModelo.decNumeroReservas3();
                    } else if (tablaReserva.getItems().get(selectedIndex).gettipoHabitacion().equals("Suite")) {
                        reservaModelo.decNumeroReservas4();
                    }
                    tablaReserva.getItems().remove(selectedIndex);
                } catch (ExcepcionHotel e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al eliminar la Cliente");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para eliminar la Cliente");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No seleccionado");
            alerta.setHeaderText("Reserva no seleccionada");
            alerta.setContentText("Selecciona una reserva.");
            alerta.showAndWait();
        }

    }

    /**
     * Metodo para generar javadoc
     *
     * @param actionEvent
     */
    public void handleAplication(ActionEvent actionEvent) throws IOException {
        mainApp.showWebView();
    }

    /**
     * Metodo para ver el porcentaje de ocupacion por mes y galeria de fotos y ocupacion de cada habitacion
     *
     * @param actionEvent
     */
    public void handleOcupation(ActionEvent actionEvent) {
        mainApp.showOcupacionTotal();
    }

    /**
     * Opcion del menu para mostrar la vista de la busquedaCliente
     *
     * @param actionEvent
     */
    public void handleBusqueda(ActionEvent actionEvent) {
        mainApp.showBusquedaCliente();
    }

    public void handleOcupacionHab(ActionEvent actionEvent) {
        mainApp.showOcupacionHab();
    }
}
