package org.example.gestionhotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.gestionhotel.MainApp;
import org.example.gestionhotel.model.ReservaModelo;
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

    @FXML
    private void initialize() {
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
    }

    private void cargarReservas() {
        // Simulación de carga de datos
        tablaReservas.setItems(listaReservas);
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
            // Obtener los valores seleccionados de los ChoiceBox como Strings
            String tipoHabitacionSeleccionado = tipoHab.getValue();  // String
            String regimenAlojamientoSeleccionado = regAlojamiento.getValue();  // String

            // Verificar si los valores seleccionados no son nulos
            if (tipoHabitacionSeleccionado != null && regimenAlojamientoSeleccionado != null) {
                // Convertir los Strings a los valores correspondientes del enum
                tipoHabitacion tipoHabitacionEnum = tipoHabitacion.valueOf(tipoHabitacionSeleccionado.toUpperCase());
                regimenAlojamiento regimenAlojamientoEnum = regimenAlojamiento.valueOf(regimenAlojamientoSeleccionado.toUpperCase());

                // Crear la nueva reserva
                Reserva nuevaReserva = new Reserva(
                        Integer.parseInt(idReserva.getText()),       // idReserva como int
                        miSpinner.getValue(),                        // numHabitaciones como int
                        fechaLlegada.getValue(),                     // llegada como LocalDate
                        fechaSalida.getValue(),                      // salida como LocalDate
                        tipoHabitacionEnum,                          // tipoHabitacion como tipoHabitacion (enum)
                        fumador.isSelected(),                        // fumador como boolean
                        regimenAlojamientoEnum,                      // regimenAlojamiento como regimenAlojamiento (enum)
                        dniCliente.getText()                         // dni como String
                );

                // Añadir la nueva reserva a la lista y actualizar la tabla
                listaReservas.add(nuevaReserva);
                tablaReservas.refresh();
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
            listaReservas.remove(seleccionada);
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ninguna reserva", AlertType.WARNING);
        }
    }

    @FXML
    private void handleEditarReserva() {
        // Obtengo la reserva seleccionada
        Reserva reservaSeleccionada = tablaReservas.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada != null) {
            // Llama al método para mostrar un diálogo de edición y obtener la reserva editada
            boolean okClicked = mainApp.mostrarDialogoEdicionReserva(reservaSeleccionada);

            if (okClicked) {
                try {
                    // Actualiza la reserva en el modelo
                    reservaModelo.modificarReserva(reservaSeleccionada);

                    // Refresca la tabla con los nuevos datos
                    tablaReservas.refresh();
                    mostrarDetallesReserva(reservaSeleccionada);
                } catch (Exception e) {
                    mostrarAlerta("Error", "No se pudo actualizar la reserva: " + e.getMessage(), Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }
        } else {
            mostrarAlerta("Advertencia", "No se ha seleccionado ninguna reserva", Alert.AlertType.WARNING);
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
