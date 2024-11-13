package org.example.gestionhotel.view;

import javafx.beans.property.*;

public class Cliente {
    StringProperty dni, nombre, apellidos, direccion, localidad, provincia;

    public Cliente(StringProperty dni, StringProperty nombre, StringProperty apellidos, StringProperty direccion,
                   StringProperty localidad, StringProperty provincia) {
        this.dni = new SimpleStringProperty(dni.get());
        this.nombre = new SimpleStringProperty(nombre.get());
        this.apellidos = new SimpleStringProperty(apellidos.get());
        this.direccion = new SimpleStringProperty(direccion.get());
        this.localidad = new SimpleStringProperty(localidad.get());
        this.provincia = new SimpleStringProperty(provincia.get());
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


}
