package com.example.demo.dobleCont;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            Stage escenarioSecundario = new Stage();

            // Crear dos instancias de Contador
            Contador cont1 = new Contador();
            Contador cont2 = new Contador();

            // Vincular ambas propiedades numPulsaciones de forma bidireccional
            cont1.numPulsacionesProperty().bindBidirectional(cont2.numPulsacionesProperty());

            // Configurar las ventanas para ambos contadores
            cont1.setStage(escenarioPrincipal);
            cont2.setStage(escenarioSecundario);

            // Mostrar ambas ventanas
            escenarioPrincipal.show();
            escenarioSecundario.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


