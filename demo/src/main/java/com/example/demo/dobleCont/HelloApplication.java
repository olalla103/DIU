package com.example.demo.dobleCont;

import com.example.demo.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // Controlador scene builder
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Contador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


    // controlador doble
    /*public void start(Stage escenarioPrincipal) {
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
}*/

}