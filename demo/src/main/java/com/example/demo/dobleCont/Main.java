package com.example.demo.dobleCont;

import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Contador cont1 = new Contador();
    Contador cont2 = new Contador();

    @Override
    public void start(Stage primaryStage) {
        cont1.setStage(primaryStage);

        Stage secondStage = new Stage();
        cont2.setStage(secondStage);
    }
}

