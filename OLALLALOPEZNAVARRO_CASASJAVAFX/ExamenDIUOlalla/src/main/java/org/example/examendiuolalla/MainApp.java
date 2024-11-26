package org.example.examendiuolalla;

import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.examendiuolalla.controller.ImagenOverViewController;
import org.example.examendiuolalla.controller.MonedaOverViewController;
import org.example.examendiuolalla.model.MonedaModelo;
import org.example.examendiuolalla.view.Moneda;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Moneda> monedaData = FXCollections.observableArrayList();
    MonedaModelo monedaModelo;


  /*  public MainApp() {
        try {
            MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
            monedaModelo = new MonedaModelo();
            monedaModelo.setMonedaRepository(monedaRepository);
            monedaData.addAll(monedaModelo.mostrarMonedas());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /**
     * Returns the data as an observable list of Monedas.
     *
     * @return
     */
    public ObservableList<Moneda> getMonedaData() {
        return monedaData;
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Moneda");

        initRootLayout();

        showMonedaOverview();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the Moneda overview inside the root layout.
     */
    public void showMonedaOverview() {
        try {
            // Load Moneda overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("MonedaOverView.fxml"));
            AnchorPane monedaOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(monedaOverview);

            // Give the controller access to the main app.
            MonedaOverViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAMonedaModelo(monedaModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showImagen() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ImagenOverView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Imagen y etiqueta");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Monedas into the controller.
            ImagenOverViewController controller = loader.getController();
            controller.setMonedaData(monedaData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}