package com.example.demo.actividadesPrueba;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CamposDeTexto extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER_LEFT);

            Label lbNombre;
            lbNombre = new Label("Nombre:");

            TextField tfNombre = new TextField();

            raiz.getChildren().addAll(lbNombre, tfNombre);

            Scene escena = new Scene(raiz, 350, 100);
            escenarioPrincipal.setTitle("Campos de texto");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
