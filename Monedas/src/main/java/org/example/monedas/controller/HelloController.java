package org.example.monedas.controller;

import Modelo.ExcepcionMoneda;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.monedas.modelo.ConversorModelo;

public class HelloController {
    @FXML
    TextField euros;

    @FXML
    TextField dolares;

    // inyectado
    private ConversorModelo conversorModelo;

    public void convierte() throws ExcepcionMoneda {
        //conversorModelo.conversor(getDatosEuros());
        // Llamar al método conversor y obtener la cantidad convertida
        Double cantidadDolares = conversorModelo.conversor(getDatosEuros());

        // Establecer el resultado en el TextField de dólares
        dolares.setText(String.format("%.2f", cantidadDolares));
    }

    // recojo los datos de los euros
    public Double getDatosEuros() {
        return Double.parseDouble(euros.getText());
    }

    public void setConversorModelo(ConversorModelo conversorModelo) {
        this.conversorModelo = conversorModelo;
    }
}