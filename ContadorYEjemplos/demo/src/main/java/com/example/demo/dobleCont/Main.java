package com.example.demo.dobleCont;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


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
