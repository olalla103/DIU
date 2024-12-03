package org.example.gestionhotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestionhotel.Reserva;
import org.example.gestionhotel.model.repository.impl.tipoHabitacion;
import org.example.gestionhotel.model.repository.impl.regimenAlojamiento;

public class ReservaEditDialogController {

    public ReservaEditDialogController() {
    }

    // Campos a editar
    @FXML
    private TextField idReservaCampo;
    @FXML
    private TextField dniCampo;
    @FXML
    private DatePicker fechaLlegadaCampo;
    @FXML
    private DatePicker fechaSalidaCampo;
    @FXML
    private ChoiceBox<tipoHabitacion> tipoHabitacionChoiceBox;
    @FXML
    private ChoiceBox<regimenAlojamiento> regimenAlojamientoChoiceBox;
    @FXML
    private CheckBox fumadorCheckBox;
    @FXML
    private Spinner<Integer> numHabitacionesSpinner;

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        // Inicializar las opciones de los ChoiceBox con los valores de los enum
        tipoHabitacionChoiceBox.getItems().setAll(tipoHabitacion.values());
        regimenAlojamientoChoiceBox.getItems().setAll(regimenAlojamiento.values());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        // Usar .get() para obtener el valor de las propiedades de JavaFX
        idReservaCampo.setText(String.valueOf(reserva.getIdReserva().get()));
        dniCampo.setText(reserva.getDni().get());
        fechaLlegadaCampo.setValue(reserva.getLlegada().get());
        fechaSalidaCampo.setValue(reserva.getSalida().get());
        tipoHabitacionChoiceBox.setValue(reserva.getTipoHabitacion());
        regimenAlojamientoChoiceBox.setValue(reserva.getRegimenAlojamiento());
        fumadorCheckBox.setSelected(reserva.getFumador().get());
        numHabitacionesSpinner.getValueFactory().setValue(reserva.getnumHabitaciones().get());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleAceptar() {
        if (isInputValid()) {
            // Actualizar los valores de la reserva con los datos del formulario
            reserva.getIdReserva().set(Integer.parseInt(idReservaCampo.getText()));
            reserva.getDni().set(dniCampo.getText());
            reserva.getLlegada().set(fechaLlegadaCampo.getValue());
            reserva.getSalida().set(fechaSalidaCampo.getValue());
            reserva.setTipoHabitacion(tipoHabitacionChoiceBox.getValue());
            reserva.setRegimenAlojamiento(regimenAlojamientoChoiceBox.getValue());
            reserva.getFumador().set(fumadorCheckBox.isSelected());
            reserva.getnumHabitaciones().set(numHabitacionesSpinner.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancelar() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        // Validaciones para los campos
        if (idReservaCampo.getText() == null || idReservaCampo.getText().isEmpty()) {
            errorMessage += "Id de reserva no válido\n";
        }
        if (dniCampo.getText() == null || dniCampo.getText().isEmpty()) {
            errorMessage += "DNI no válido\n";
        }
        if (fechaLlegadaCampo.getValue() == null) {
            errorMessage += "Fecha de llegada no válida\n";
        }
        if (fechaSalidaCampo.getValue() == null) {
            errorMessage += "Fecha de salida no válida\n";
        }
        if (tipoHabitacionChoiceBox.getValue() == null) {
            errorMessage += "Tipo de habitación no válido\n";
        }
        if (regimenAlojamientoChoiceBox.getValue() == null) {
            errorMessage += "Régimen de alojamiento no válido\n";
        }
        if (numHabitacionesSpinner.getValue() == null || numHabitacionesSpinner.getValue() <= 0) {
            errorMessage += "Número de habitaciones no válido\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos no válidos");
            alert.setHeaderText("Campos no válidos");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
