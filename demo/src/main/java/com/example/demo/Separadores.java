package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Separadores extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);

            Label lbElige;
            lbElige = new Label("Elige el día:");
            lbElige.setFont(Font.font(20));

            ComboBox<Object> cbExtras = new ComboBox<>();
            Separator separador = new Separator();
            ObservableList<Object> laborables = FXCollections.observableArrayList(
                    "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", separador, "Sábado", "Domingo");
            cbExtras.getItems().addAll(laborables);

            raiz.getChildren().addAll(lbElige, cbExtras);

            Scene escena = new Scene(raiz, 350, 200);
            primaryStage.setTitle("Separadores");
            primaryStage.setScene(escena);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
