package com.example.agenda.model.repository;

import javafx.scene.control.Alert;

public class ExceptionPerson extends RuntimeException {
    private Alert mensajeA;
    private String mensaje;

    /**
     * Constructor vacio
     */
    public ExceptionPerson() {
    }

    /**
     * Constructor que recibe un mensaje
     *
     * @param ms
     */
    public ExceptionPerson(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }

    public ExceptionPerson(Alert error) {
        this.mensajeA = error;
    }
}
