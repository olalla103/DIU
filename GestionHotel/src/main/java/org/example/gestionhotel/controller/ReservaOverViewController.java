package org.example.gestionhotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ReservaModelo;
import org.example.gestionhotel.model.repository.ExcepcionHotel;
import org.example.gestionhotel.model.repository.impl.regimenAlojamiento;
import org.example.gestionhotel.model.repository.impl.tipoHabitacion;
import org.example.gestionhotel.Reserva;

import java.time.LocalDate;

public class ReservaOverViewController {
    private ReservaModelo reservaModelo;
    @FXML
    private TableView<Reserva> tablaReservas; // Tabla para mostrar las reservas
    @FXML
    private TableColumn<Reserva, Integer> columnaIdReserva;
    @FXML
    private TableColumn<Reserva, LocalDate> columnaLlegada;
    @FXML
    private TableColumn<Reserva, LocalDate> columnaSalida;

    @FXML
    private TextField idReserva;
    @FXML
    private TextField dniCliente;
    @FXML
    private DatePicker fechaLlegada;
    @FXML
    private DatePicker fechaSalida;
    @FXML
    private ChoiceBox<String> tipoHab;
    @FXML
    private ChoiceBox<String> regAlojamiento;
    @FXML
    private CheckBox fumador;
    @FXML
    private Spinner<Integer> miSpinner;

    @FXML
    private Button aniadirReserva;
    @FXML
    private Button eliminarReserva;
    @FXML
    private Button editarReserva;

    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
    private MainApp mainApp;

    // Inyección del modelo en el controlador
    public void setReservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;

        // Intentar cargar las reservas cuando se reciba el modelo
        cargarReservas();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        if (reservaModelo == null) {
            reservaModelo = new ReservaModelo();  // Crea una nueva instancia
        }
        // Inicializar el spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1); // Rango de 1 a 10, valor inicial 1
        miSpinner.setValueFactory(valueFactory);

        // Inicializar las columnas de la tabla de reservas con las propiedades correspondientes
        columnaIdReserva.setCellValueFactory(cellData -> cellData.getValue().idReservaProperty().asObject());  // Convertir IntegerProperty a ObservableValue<Integer>
        columnaLlegada.setCellValueFactory(cellData -> cellData.getValue().llegadaProperty());
        columnaSalida.setCellValueFactory(cellData -> cellData.getValue().salidaProperty());

        // Limpiar los detalles de la reserva
        mostrarDetallesReserva(null);

        // Escuchar los cambios de selección y mostrar los detalles de la reserva cuando cambie la selección
        tablaReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesReserva(newValue));

        // Cargar las reservas al inicializar
        cargarReservas();

        // Inicializar los ChoiceBox
        tipoHab.getItems().addAll("Individual", "Doble", "Suite");  // Opciones para el tipo de habitación
        regAlojamiento.getItems().addAll("Desayuno incluido", "Solo alojamiento", "Pensión completa");  // Opciones para el régimen de alojamiento
    }

    private void cargarReservas() {
        try {
            // Obtener las reservas desde el modelo
            listaReservas.setAll(reservaModelo.mostrarReservas());
            tablaReservas.setItems(listaReservas);
        } catch (ExcepcionHotel e) {
            mostrarAlerta("Error", "No se pudo cargar la lista de reservas: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private void mostrarDetallesReserva(Reserva reserva) {
        if (reserva != null) {
            idReserva.setText(String.valueOf(reserva.getIdReserva()));
            dniCliente.setText(reserva.getDni().get());
            fechaLlegada.setValue(reserva.getLlegada().get());
            fechaSalida.setValue(reserva.getSalida().get());
            tipoHab.setValue(reserva.getTipoHabitacion().name());
            regAlojamiento.setValue(reserva.getRegimenAlojamiento().name());
            fumador.setSelected(reserva.getFumador().get());
            miSpinner.getValueFactory().setValue(reserva.getnumHabitaciones().getValue());
        } else {
            limpiarCampos();
        }
    }


    private void limpiarCampos() {
        idReserva.clear();
        dniCliente.clear();
        fechaLlegada.setValue(null);
        fechaSalida.setValue(null);
        tipoHab.setValue(null);
        regAlojamiento.setValue(null);
        fumador.setSelected(false);
        miSpinner.getValueFactory().setValue(1);
    }

    @FXML
    private void handleAniadirReserva() {
        try {
            String tipoHabitacionSeleccionado = tipoHab.getValue();
            String regimenAlojamientoSeleccionado = regAlojamiento.getValue();

            if (tipoHabitacionSeleccionado != null && regimenAlojamientoSeleccionado != null) {
                tipoHabitacion tipoHabitacionEnum = tipoHabitacion.valueOf(tipoHabitacionSeleccionado.toUpperCase());
                regimenAlojamiento regimenAlojamientoEnum = regimenAlojamiento.valueOf(regimenAlojamientoSeleccionado.toUpperCase());

                Reserva nuevaReserva = new Reserva(
                        Integer.parseInt(idReserva.getText()),
                        miSpinner.getValue(),
                        fechaLlegada.getValue(),
                        fechaSalida.getValue(),
                        tipoHabitacionEnum,
                        fumador.isSelected(),
                        regimenAlojamientoEnum,
                        dniCliente.getText()
                );

                // Insertar la nueva reserva a través del modelo
                reservaModelo.insertarReserva(nuevaReserva);

                // Recargar la lista
                cargarReservas();
            } else {
                mostrarAlerta("Error", "Debe seleccionar un tipo de habitación y un régimen de alojamiento.", AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo añadir la reserva: " + e.getMessage(), AlertType.ERROR);
        }
    }


    @FXML
    private void handleEliminarReserva() {
        Reserva seleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            try {
                reservaModelo.eliminarReserva(seleccionada.getIdReserva().get());
                cargarReservas(); // Recargar la lista después de eliminar
            } catch (ExcepcionHotel e) {
                mostrarAlerta("Error", "No se pudo eliminar la reserva: " + e.getMessage(), AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ninguna reserva", AlertType.WARNING);
        }
    }

    @FXML
    private void handleEditarReserva() {
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada != null) {
            boolean okClicked = mainApp.mostrarDialogoEdicionReserva(reservaSeleccionada);

            if (okClicked) {
                try {
                    reservaModelo.modificarReserva(reservaSeleccionada);
                    cargarReservas(); // Recargar la lista después de editar
                } catch (ExcepcionHotel e) {
                    mostrarAlerta("Error", "No se pudo actualizar la reserva: " + e.getMessage(), AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ninguna reserva", AlertType.WARNING);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public Button getEditarReserva() {
        return editarReserva;
    }

    public void setEditarReserva(Button editarReserva) {
        this.editarReserva = editarReserva;
    }

    public Button getEliminarReserva() {
        return eliminarReserva;
    }

    public void setEliminarReserva(Button eliminarReserva) {
        this.eliminarReserva = eliminarReserva;
    }

    public Button getAniadirReserva() {
        return aniadirReserva;
    }

    public void setAniadirReserva(Button aniadirReserva) {
        this.aniadirReserva = aniadirReserva;
    }
}
