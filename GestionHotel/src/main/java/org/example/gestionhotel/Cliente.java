package org.example.gestionhotel;

import javafx.beans.property.*;

public class Cliente {
    StringProperty dni, nombre, apellidos, direccion, localidad, provincia;

    public Cliente(String dni, String nombre, String apellidos, String direccion,
                   String localidad, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);
    }

    public Cliente() {
        this("", "", "", "", "", "");
    }


    public StringProperty getDni() {
        return dni;
    }

    public void setDni(StringProperty dni) {
        this.dni = dni;
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public StringProperty getApellidos() {
        return apellidos;
    }

    public void setApellidos(StringProperty apellidos) {
        this.apellidos = apellidos;
    }

    public StringProperty getDireccion() {
        return direccion;
    }

    public void setDireccion(StringProperty direccion) {
        this.direccion = direccion;
    }

    public StringProperty getLocalidad() {
        return localidad;
    }

    public void setLocalidad(StringProperty localidad) {
        this.localidad = localidad;
    }

    public StringProperty getProvincia() {
        return provincia;
    }

    public void setProvincia(StringProperty provincia) {
        this.provincia = provincia;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidosProperty() {
        return apellidos;
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
