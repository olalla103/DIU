package com.example.demo.dobleCont;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Contador {

    // CONTAMOS PULSACIONES
    Label etiqueta = new Label();
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(0);

    public IntegerProperty numeroProperty() {
        return numPulsaciones;
    }

    public int getNumPulsaciones() {
        int n = numPulsaciones.get();
        return n;
    }

    public IntegerProperty numPulsacionesProperty() {
        return numPulsaciones;
    }

    private void contandoPulsaciones(int n) {
        numPulsaciones.set(n == 0 ? 0 : numPulsaciones.get() + n);
        etiqueta.setText(String.valueOf(numPulsaciones));
    }


    public void setStage(Stage primaryStage) {
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

        // AÑADO ACCIÓN A LOS BOTONES
        btTextoMas.setOnAction(e -> contandoPulsaciones(1));
        btTextoMenos.setOnAction(e -> contandoPulsaciones(-1));
        btTextoCero.setOnAction(e -> contandoPulsaciones(0));

        // EL CONTADOR EMPIEZA EN 0
        etiqueta.setText("0");

        // METO EN EL HORIZONTAL LOS BOTONES
        horizontal.getChildren().addAll(btTextoMas, btTextoMenos, btTextoCero);

        // AÑADO AL VERTICAL EL HORIZONTAL Y LA ETIQUETA DEL NÚMERO
        vertical.getChildren().addAll(horizontal, etiqueta);
        vertical.getStyleClass().add("fondoPestania");

        // MUESTRO LA ESCENA
        Scene escena = new Scene(vertical, 400, 120);
        primaryStage.setTitle("Contador");
        primaryStage.setScene(escena);
        escena.getStylesheets().add(getClass().getResource("/styles/estiloContador.css").toExternalForm());
        etiqueta.getStyleClass().add("uno");
        btTextoCero.getStyleClass().add("fondoBoton");
        primaryStage.show();

    }
}
