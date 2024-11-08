package org.example.gestionhotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.gestionhotel.model.repository.ClienteRepository;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.util.ClienteVO;
import org.example.gestionhotel.view.Cliente;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        // recuperar clientes de la base de datos
        ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        System.out.println("Clientes: " + clienteRepository.ObtenerListaClientes() + "\n");

        // añadir un cliente
        ClienteVO papa = new ClienteVO("12345678B", "Valentín", "López Meneses", "José Gallego Góngora 17"
                , "Villanueva del Ariscal", "Sevilla");
        clienteRepository.aniadirCliente(papa);
        System.out.println(clienteRepository.ObtenerListaClientes() + "\n");

        // eliminar un cliente
        // ME DA ERROR LA ELIMINACIÓN
        clienteRepository.eliminarCliente("12345678B");
        System.out.println(clienteRepository.ObtenerListaClientes() + "\n");

        launch();
    }
}