package org.example.gestionhotel.model.util;

import org.example.gestionhotel.model.repository.impl.regimenAlojamiento;
import org.example.gestionhotel.model.repository.impl.tipoHabitacion;

import java.time.LocalDate;

public class ReservaVO {

    String idReserva;
    LocalDate llegada;
    LocalDate salida;
    tipoHabitacion tipoHabitacion;
    Integer numeroHabitaciones;
    Boolean fumador;
    regimenAlojamiento regimenAlojamiento;
    ClienteVO cliente;
    String dni;

    public ReservaVO(String idReserva, LocalDate llegada, LocalDate salida, org.example.gestionhotel.model.repository.impl.tipoHabitacion
            tipoHabitacion, Integer numeroHabitaciones, Boolean fumador,
                     org.example.gestionhotel.model.repository.impl.regimenAlojamiento regimenAlojamiento, String dni) {
        this.idReserva = idReserva;
        this.llegada = llegada;
        this.salida = salida;
        this.tipoHabitacion = tipoHabitacion;
        this.numeroHabitaciones = numeroHabitaciones;
        this.fumador = fumador;
        this.regimenAlojamiento = regimenAlojamiento;
        this.dni = cliente.getDni();
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getLlegada() {
        return llegada;
    }

    public void setLlegada(LocalDate llegada) {
        this.llegada = llegada;
    }

    public LocalDate getSalida() {
        return salida;
    }

    public void setSalida(LocalDate salida) {
        this.salida = salida;
    }

    public org.example.gestionhotel.model.repository.impl.tipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(org.example.gestionhotel.model.repository.impl.tipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Integer getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Boolean getFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public org.example.gestionhotel.model.repository.impl.regimenAlojamiento getRegimenAlojamiento() {
        return regimenAlojamiento;
    }

    public void setRegimenAlojamiento(org.example.gestionhotel.model.repository.impl.regimenAlojamiento regimenAlojamiento) {
        this.regimenAlojamiento = regimenAlojamiento;
    }
}
