package com.example.gestionhotelolalla.modelo;

import javafx.scene.control.Alert;

public class ExcepcionHotel extends RuntimeException {
  private Alert mensajeA;
  private String mensaje;

  /**
   * Constructor vacio de la ExcepcionHotel
   */
  public ExcepcionHotel() {

  }

  /**
   * Muestra el mensaje
   *
   * @param ms
   */
  public ExcepcionHotel(String ms) {
    this.mensaje = ms;
  }

  public String imprimirMensaje() {
    return this.mensaje;
  }

  public ExcepcionHotel(Alert error) {
    this.mensajeA = error;
  }
}
