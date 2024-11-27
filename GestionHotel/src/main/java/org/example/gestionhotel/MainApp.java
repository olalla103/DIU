package org.example.gestionhotel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.gestionhotel.controller.ClienteOverviewController;
import org.example.gestionhotel.controller.RootLayoutController;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.view.Cliente;

import java.io.IOException;

public class MainApp extends Application {
    // VARIABLES
    private Stage primaryStage; // Escenario principal
    private BorderPane rootLayout; // Cambiado a Pane porque no usas BorderPane como nodo raíz
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    ClienteModelo clienteModelo;

    // Constructor
    public MainApp() {
        try {
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            clienteModelo = new ClienteModelo();
            clienteModelo.setClienteRepository(clienteRepository);
            clienteData.addAll(clienteModelo.mostrarClientes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Inicializa el escenario principal
        this.primaryStage.setTitle("Gestión Hotel");

        initRootLayout(); // Inicializamos el layout principal
        showClienteOverview(); // Mostramos el contenido del ClienteOverview
    }

    // Inicializamos el RootLayout
    public void initRootLayout() {
        try {
            // Carga el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("rootLayout.fxml"));
            rootLayout = loader.load(); // Carga como Pane

            // Mostrar escena en el escenario principal
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Inyectar modelo en el controlador del RootLayout
            RootLayoutController controller = loader.getController();
            controller.setClienteModelo(clienteModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Muestra el ClienteOverview dentro del rootLayout
    public void showClienteOverview() {
        try {
            // Cargar el archivo FXML del ClienteOverview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("clienteOverView.fxml"));
            AnchorPane clienteOverview = loader.load(); // Se espera que sea un AnchorPane

            // Añadir el clienteOverview al RootLayout
            rootLayout.setCenter(clienteOverview);

            // Inyectar modelo y referencia al controlador
            ClienteOverviewController controller = loader.getController();
            System.out.println("hola juan");
            controller.setMainApp(this);
            controller.setClienteModelo(clienteModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para acceder a la lista de clientes
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
