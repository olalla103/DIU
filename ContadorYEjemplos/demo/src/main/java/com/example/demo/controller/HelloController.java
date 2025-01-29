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
    @FXML
    private Button btTextoMas;
    @FXML
    private Button btTextoMenos;
    @FXML
    private Button btTextoCero;
    @FXML
    private TextField campoTexto;
    @FXML
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

    // binding


    // Método para inicializar los contadores
    /*public void initialize() {
        cont1 = new Contador();
        cont2 = new Contador();

        // Vincular ambas propiedades numPulsaciones de forma bidireccional
        cont1.numPulsacionesProperty().bindBidirectional(cont2.numPulsacionesProperty());

        // Actualizar la etiqueta para que refleje el valor del primer contador
        etiquetaUno.textProperty().bind(cont1.numPulsacionesProperty().asString());

        // Establecer acciones de los botones
        btTextoMas.setOnAction(e -> cont1.contandoPulsaciones(1));
        btTextoMenos.setOnAction(e -> cont1.contandoPulsaciones(-1));
        btTextoCero.setOnAction(e -> cont1.contandoPulsaciones(0));
    }*/
}

