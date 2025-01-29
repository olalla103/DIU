package com.example.gestionHotelOlallaLopez.modelo;

import javafx.beans.property.*;

import java.time.LocalDate;

import com.example.gestionHotelOlallaLopez.modelo.repository.impl.tipoHabitacion;
import com.example.gestionHotelOlallaLopez.modelo.repository.impl.regimenAlojamiento;

public class Reserva {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> fechaLlegada;
    private ObjectProperty<LocalDate> fechaFin;
    private IntegerProperty numHabitacion;
    private com.example.gestionHotelOlallaLopez.modelo.repository.impl.tipoHabitacion tipoHabitacion; // Cambiado a StringProperty
    private BooleanProperty fumador;
    private com.example.gestionHotelOlallaLopez.modelo.repository.impl.regimenAlojamiento regimenAlojamiento; // Cambiado a StringProperty
    private StringProperty DNICliente;

    // Constructor
    public Reserva(Integer id, LocalDate fechaLlegada, LocalDate fechaFin, Integer numHabitacion,
                   tipoHabitacion tipoHabitacion, Boolean fumador, regimenAlojamiento regimenAlojamiento, String DNICliente) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaLlegada = new SimpleObjectProperty<>(fechaLlegada);
        this.fechaFin = new SimpleObjectProperty<>(fechaFin);
        this.numHabitacion = new SimpleIntegerProperty(numHabitacion);
        this.tipoHabitacion = tipoHabitacion; // Almacena como String
        this.fumador = new SimpleBooleanProperty(fumador);
        this.regimenAlojamiento = regimenAlojamiento; // Almacena como String
        this.DNICliente = new SimpleStringProperty(DNICliente);
    }

    // Métodos getter y setter
    public StringProperty tipoHabitacionProperty() {
        // Convertir el valor del enum a String
        return new SimpleStringProperty(tipoHabitacion != null ? tipoHabitacion.name() : "");
    }

    public StringProperty regimenAlojamientoProperty() {
        // Convertir el valor del enum a String
        return new SimpleStringProperty(regimenAlojamiento != null ? regimenAlojamiento.name() : "");
    }


    public Reserva() {
        this(0, null, null, 0, null, false, null, null);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada.get();
    }

    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public LocalDate getFechaFin() {
        return fechaFin.get();
    }

    public ObjectProperty<LocalDate> fechaFinProperty() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin.set(fechaFin);
    }

    public int getNumHabitacion() {
        return numHabitacion.get();
    }

    public IntegerProperty numHabitacionProperty() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion.set(numHabitacion);
    }

    public com.example.gestionHotelOlallaLopez.modelo.repository.impl.tipoHabitacion gettipoHabitacion() {
        return tipoHabitacion;
    }

    public void settipoHabitacion(tipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public boolean isFumador() {
        return fumador.get();
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }

    public com.example.gestionHotelOlallaLopez.modelo.repository.impl.regimenAlojamiento getregimenAlojamiento() {
        return regimenAlojamiento;
    }


    public void setregimenAlojamiento(regimenAlojamiento regimenAlojamiento) {
        this.regimenAlojamiento = regimenAlojamiento;
    }

    public String getDNICliente() {
        return DNICliente.get();
    }

    public StringProperty DNIClienteProperty() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente.set(DNICliente);
    }
}
