package org.example.gestionhotel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.gestionhotel.controller.ClientEditDialogController;
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

    public Stage getPrimaryStage() {
        return primaryStage;
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
            // Cargar el archivo FXML de reservaOverview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("reservaOverView.fxml"));
            AnchorPane reservaOverview = loader.load(); // Se espera que sea un AnchorPane

            // Añadir la vista de reservas al RootLayout (puedes cambiarlo si quieres usar otro contenedor)
            rootLayout.setCenter(reservaOverview);

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


    // Método para acceder a la lista de clientes
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*   public boolean mostrarDialogoEdicionCliente(Cliente cliente) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/gestionhotel/clientEditDialog.fxml"));
            Parent page = loader.load();

            // Crear el diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage); // primaryStage es la ventana principal
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Pasar el cliente al controlador
            ClientEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            // Mostrar el diálogo y esperar hasta que el usuario lo cierre
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }*/
