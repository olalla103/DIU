package org.example.gestionhotel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.gestionhotel.controller.ClienteOverviewController;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.repository.ClienteRepository;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.view.Cliente;

import java.io.IOException;

public class MainApp extends Application {
    // VARIABLES
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    ClienteModelo clienteModelo = new ClienteModelo();

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
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("clienteOverView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gestión Hotel");

        initRootLayout();
        showClienteOverview();

        stage.setScene(scene);
        stage.show();
    }

    // Inicializamos el RootLayout
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("rootlayout.fxml"));
            rootLayout = (BorderPane)  loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // mete el Cliente OverView dentro del rootLayout, tendría que cambiarlo y poner otra pantalla
    public void showClienteOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            ClienteOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setClienteModelo(clienteModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Instancia del modelo y repositorio
      /*  ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        ClienteModelo clienteModelo = new ClienteModelo();
        clienteModelo.setClienteRepository(clienteRepository);

        // Inyectar modelo en el controlador
        ClienteOverviewController controller = fxmlLoader.getController();
        controller.setClienteModelo(clienteModelo);*/
}


