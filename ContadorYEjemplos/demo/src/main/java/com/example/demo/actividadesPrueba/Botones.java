package com.example.demo.actividadesPrueba;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Botones extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {

            // AQUÍ CREO UNA VENTANA HORIZONTAL
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(20, 20, 20, 20));
            raiz.setSpacing(10);
            raiz.setAlignment(Pos.CENTER);

            // CREACIÓN DE LOS BOTONES
            Button btTexto, btTextoImagen, btImagen;
            btTexto = new Button();
            btTextoImagen = new Button();
            btImagen = new Button();

            btTexto.setText("Pulsa");

            Image imagen = new Image("file:resources/imagenes/zorroSlay.jpg", 60, 60, true, true);
            btTextoImagen.setGraphic(new ImageView(imagen));
            btTextoImagen.setText("Slay");
            btTextoImagen.setGraphicTextGap(20);
            btTextoImagen.setFont(Font.font("Ani", 30));

            imagen = new Image("file:resources/imagenes/gumball.jpg", 60, 60, true, true);
            btImagen.setGraphic(new ImageView(imagen));

            raiz.getChildren().addAll(btTexto, btTextoImagen, btImagen);

            Scene escena = new Scene(raiz, 400, 120);
            escenarioPrincipal.setTitle("Botones");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
