package org.example.gestionhotel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.gestionhotel.controller.*;
import org.example.gestionhotel.model.ClienteModelo;
import org.example.gestionhotel.model.ReservaModelo;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.repository.impl.ReservaRepositoryImpl;

import java.io.IOException;

public class MainApp extends Application {
    // VARIABLES
    private Stage primaryStage; // Escenario principal
    private BorderPane rootLayout; // Cambiado a Pane porque no usas BorderPane como nodo raíz
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    ClienteModelo clienteModelo;
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();
    ReservaModelo reservaModelo;

    // Constructor
    public MainApp() {
        try {
            // Inicialización cliente
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            clienteModelo = new ClienteModelo();
            clienteModelo.setClienteRepository(clienteRepository);
            clienteData.addAll(clienteModelo.mostrarClientes());

            // Inicialización Reserva
            ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
            reservaModelo = new ReservaModelo();
            reservaModelo.setReservaRepository(reservaRepository);
            reservaData.addAll(reservaModelo.mostrarReservas());
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    // Inicializamos el RootLayout
   /* public void initRootLayout() {
        try {
            // Carga el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("rootLayout.fxml"));
            rootLayout = loader.load(); // Carga como Pane

            // Mostrar escena en el escenario principal
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Inyectar el MainApp en el controlador de RootLayout
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void initRootLayout() {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("rootLayout.fxml"));
            rootLayout = loader.load();

            // Crear la escena y añadir el rootLayout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Configurar tamaño inicial y mínimo de la ventana
            primaryStage.setTitle("Gestión Hotel");
            primaryStage.setMinWidth(100); // Ancho mínimo
            primaryStage.setMinHeight(200); // Alto mínimo

            // Ajustar dinámicamente el tamaño del rootLayout
            rootLayout.prefWidthProperty().bind(scene.widthProperty());
            rootLayout.prefHeightProperty().bind(scene.heightProperty());

            // Mostrar la ventana principal
            primaryStage.show();

            // Inyectar el MainApp en el controlador de RootLayout
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

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
            AnchorPane clienteOverview = loader.load();

            // Añadir el clienteOverview al RootLayout
            rootLayout.setCenter(clienteOverview);

            // Inyectar modelo y referencia al controlador
            ClienteOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setClienteModelo(clienteModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar la vista de reservas
    public void showReservaOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("reservaOverView.fxml"));
            AnchorPane reservaOverview = loader.load();

            rootLayout.setCenter(reservaOverview);

            ReservaOverViewController controller = loader.getController();
            controller.setMainApp(this);

            // Inyectar el modelo
            controller.setReservaModelo(reservaModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean mostrarDialogoEdicionCliente(Cliente cliente) {
        try {
            // Cargar el archivo FXML de edición
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("clientEditDialog.fxml")); // Ruta al FXML
            Parent page = loader.load();

            // Crear la ventana de diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Pasar el cliente al controlador
            ClientEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            // Mostrar y esperar hasta que se cierre
            dialogStage.showAndWait();

            return controller.isOkClicked(); // Retorna si se confirmó la edición
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarDialogoEdicionReserva(Reserva reserva) {
        try {
            // Cargar el archivo FXML de edición
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("reservaEditDialog.fxml")); // Ruta al FXML
            Parent page = loader.load();

            // Crear la ventana de diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Pasar el cliente al controlador
            ReservaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReserva(reserva);

            // Mostrar y esperar hasta que se cierre
            dialogStage.showAndWait();

            return controller.isOkClicked(); // Retorna si se confirmó la edición
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para acceder a la lista de clientes
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }

    public ObservableList<Reserva> getReservaData() {
        return reservaData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}