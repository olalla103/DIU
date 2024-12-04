package com.example.gestionhotelolalla.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private StringProperty dni;
    private StringProperty nombre;
    private StringProperty apellidos;
    private StringProperty direccion;
    private StringProperty localidad;
    private StringProperty provincia;


    /**
     * Constructor de la Cliente
     *
     * @param dni
     * @param nombre
     * @param apellidos
     * @param direccion
     * @param localidad
     * @param provincia
     */
    public Cliente(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);
    }

    /**
     * Constructor de la Cliente vacio
     */
    public Cliente() {
        this(null, null, null, null, null, null);
    }

    public String getdni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setdni(String dni) {
        this.dni.set(dni);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public String getProvincia() {
        return provincia.get();
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni=" + dni +
                ", nombre=" + nombre +
                ", apellidos=" + apellidos +
                ", direccion=" + direccion +
                ", localidad=" + localidad +
                ", provincia=" + provincia +
                '}';
    }
}
