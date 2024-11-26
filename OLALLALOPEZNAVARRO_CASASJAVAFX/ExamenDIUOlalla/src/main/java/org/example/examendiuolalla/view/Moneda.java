package org.example.examendiuolalla.view;

import javafx.beans.property.*;


public class Moneda {
    private final IntegerProperty codigo;
    private final StringProperty nombre;
    private final FloatProperty multiplicador;

    public Moneda(String nombre, Float multiplicador, Integer codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public Moneda() {
        this(null, null, 0);
    }


    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public float getMultiplicador() {
        return multiplicador.get();
    }

    public FloatProperty multiplicadorProperty() {
        return multiplicador;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }
}
