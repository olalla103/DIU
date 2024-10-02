package com.example.demo.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label etiquetaUno;
    private Button btTextoMas, btTextoMenos, btTextoCero;
    private TextField campoTexto;
    private ProgressBar barraProgreso;

    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(0);

    public IntegerProperty numeroProperty() {
        return numPulsaciones;
    }

    private void contandoPulsaciones(int n) {
        numPulsaciones.set(n == 0 ? 0 : numPulsaciones.get() + n);
        etiquetaUno.setText(String.valueOf(numPulsaciones.get()));
    }

    @FXML
    protected void botones() {
        btTextoMas.setOnAction(e -> contandoPulsaciones(1));
        btTextoMenos.setOnAction(e -> contandoPulsaciones(-1));
        btTextoCero.setOnAction(e -> contandoPulsaciones(0));
    }

}