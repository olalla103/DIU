package org.example.gestionhotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.gestionhotel.controller.ClienteOverviewController;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.repository.ClienteRepository;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("clienteOverView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gestión Hotel");

        // Instancia del modelo y repositorio
        ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        ClienteModelo clienteModelo = new ClienteModelo();
        clienteModelo.setClienteRepository(clienteRepository);

        // Inyectar modelo en el controlador
        ClienteOverviewController controller = fxmlLoader.getController();
        controller.setClienteModelo(clienteModelo);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


