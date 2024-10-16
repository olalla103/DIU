package org.example.monedas;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ExcepcionMoneda {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Conversor");

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws ExcepcionMoneda {

        // CONEXION A LA BASE DE DATOS
        ConexionJDBC conexion = new ConexionJDBC();
        Connection connection = null;
        try {
            connection = conexion.conectarBD();
            //Añadimos una moneda a la base de datos
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("hola", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);
            conexion.desconectarBD(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        launch();
    }
}

