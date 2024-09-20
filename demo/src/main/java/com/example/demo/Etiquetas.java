package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

public class Etiquetas extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(50, 50, 50, 50));
            raiz.setSpacing(30);

            Label lbImagen, lbRotada, lbCortada;
            lbImagen = new Label();
            lbRotada = new Label();
            lbCortada = new Label();

            Image imagen = new Image("file:resources/imagenes/fotoPescao.jpg", 100, 100, true, true);
            lbImagen.setGraphic(new ImageView(imagen));

            lbRotada.setText("Etiqueta");
            lbRotada.setFont(Font.font("Ani", 20));
            lbRotada.setMinWidth(150);
            lbRotada.setRotate(-50);

            lbCortada.setWrapText(true);
            lbCortada.setText("Esta es otra etiqueta que no cabe y debe ser cortada en varias líneas");

            raiz.getChildren().addAll(lbImagen, lbRotada, lbCortada);

            Scene escena = new Scene(raiz, 500, 200);
            escenarioPrincipal.setTitle("Towel");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
