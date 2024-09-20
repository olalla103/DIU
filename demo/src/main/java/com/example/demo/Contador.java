package com.example.demo;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Contador extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // VENTANA VERTICAL
        VBox vertical = new VBox();
        vertical.setPadding(new Insets(20, 20, 20, 20));
        vertical.setSpacing(10);
        vertical.setAlignment(Pos.CENTER);

        // VENTANA HORIZONTAL
        HBox horizontal = new HBox();
        horizontal.setPadding(new Insets(20, 20, 20, 20));
        horizontal.setSpacing(10);
        horizontal.setAlignment(Pos.CENTER);

        // BOTONES
        Button btTextoMas, btTextoMenos, btTextoCero;
        btTextoMas = new Button();
        btTextoMenos = new Button();
        btTextoCero = new Button();

        btTextoMas.setText("+");
        btTextoMenos.setText("-");
        btTextoCero.setText("0");

        // ETIQUETA DEL NÚMERO QUE SERÁ REEMPLAZADA 
        Label etiqueta;
        etiqueta = new Label();
        etiqueta.setText("1");

        // METO EN EL HORIZONTAL LOS BOTONES
        horizontal.getChildren().addAll(btTextoMas, btTextoMenos, btTextoCero);

        // AÑADO AL VERTICAL EL HORIZONTAL Y LA ETIQUETA DEL NÚMERO
        vertical.getChildren().addAll(horizontal, etiqueta);

        // MUESTRO LA ESCENA
        Scene escena = new Scene(vertical, 400, 120);
        primaryStage.setTitle("Contador");
        primaryStage.setScene(escena);
        primaryStage.show();

    }
}
