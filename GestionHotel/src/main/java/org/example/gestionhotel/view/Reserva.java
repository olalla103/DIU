package org.example.gestionhotel.view;

import org.example.gestionhotel.model.util.ClienteVO;
import javafx.beans.property.*;

import java.time.LocalDate;


public class Reserva {

    // ATRIBUTOS DE RESERVA
    IntegerProperty idReserva, numHabitaciones;
    private ObjectProperty<LocalDate> llegada;
    private ObjectProperty<LocalDate> salida;
    org.example.gestionhotel.model.repository.impl.tipoHabitacion tipoHabitacion;
    BooleanProperty fumador;
    org.example.gestionhotel.model.repository.impl.regimenAlojamiento regimenAlojamiento;
    StringProperty dni;

    // CONSTRUCTOR


    public Reserva(IntegerProperty idReserva, IntegerProperty numHabitaciones, LocalDate llegada, LocalDate salida,
                   org.example.gestionhotel.model.repository.impl.tipoHabitacion tipoHabitacion,
                   BooleanProperty fumador, org.example.gestionhotel.model.repository.impl.regimenAlojamiento regimenAlojamiento,
                   ClienteVO cliente,
                   StringProperty dni) {
        this.idReserva = new SimpleIntegerProperty(idReserva.get());
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones.get());
        this.llegada = new SimpleObjectProperty<LocalDate>(llegada);
        this.salida = new SimpleObjectProperty<LocalDate>(salida);
        this.tipoHabitacion = tipoHabitacion;
        this.fumador = new SimpleBooleanProperty(fumador.get());
        this.regimenAlojamiento = regimenAlojamiento;
        this.dni = new SimpleStringProperty(dni.get());
    }


    public StringProperty getDni() {
        return dni;
    }

    public void setDni(StringProperty dni) {
        this.dni = dni;
    }

    public IntegerProperty getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(IntegerProperty idReserva) {
        this.idReserva = idReserva;
    }

    public ObjectProperty<LocalDate> getLlegada() {
        return llegada;
    }

    public void setLlegada(ObjectProperty<LocalDate> llegada) {
        this.llegada = llegada;
    }

    public ObjectProperty<LocalDate> getSalida() {
        return salida;
    }

    public void setSalida(ObjectProperty<LocalDate> salida) {
        this.salida = salida;
    }

    public org.example.gestionhotel.model.repository.impl.tipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(org.example.gestionhotel.model.repository.impl.tipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public IntegerProperty getnumHabitaciones() {
        return numHabitaciones;
    }

    public void setnumHabitaciones(IntegerProperty numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public BooleanProperty getFumador() {
        return fumador;
    }

    public void setFumador(BooleanProperty fumador) {
        this.fumador = fumador;
    }

    public org.example.gestionhotel.model.repository.impl.regimenAlojamiento getRegimenAlojamiento() {
        return regimenAlojamiento;
    }

    public void setRegimenAlojamiento(org.example.gestionhotel.model.repository.impl.regimenAlojamiento regimenAlojamiento) {
        this.regimenAlojamiento = regimenAlojamiento;
    }

    @Override
    public String toString() {
        return "ReservaVO{" +
                "idReserva='" + idReserva + '\'' +
                ", llegada=" + llegada +
                ", salida=" + salida +
                ", tipoHabitacion=" + tipoHabitacion +
                ", numHabitaciones=" + numHabitaciones +
                ", fumador=" + fumador +
                ", regimenAlojamiento=" + regimenAlojamiento +
                ", dni='" + dni + '\'' +
                '}';
    }
}


