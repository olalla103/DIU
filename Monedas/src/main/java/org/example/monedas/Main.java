package org.example.monedas;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.monedas.controller.HelloController;
import org.example.monedas.modelo.ConversorModelo;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ExcepcionMoneda {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloController controller = fxmlLoader.getController();
            ConversorModelo conversor = new ConversorModelo();
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            conversor.setM(monedarepositoryImpl);
            controller.setConversorModelo(conversor);
            stage.setTitle("Conversor");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No se ha podido recuperar el cambio de moneda");
            alert.setContentText(e.getMessage());
            alert.showAndWait();


        }
    }

    public static void main(String[] args) throws ExcepcionMoneda {
        launch(args);
    }
}

