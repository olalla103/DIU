package org.example.examendiuolalla.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.examendiuolalla.MainApp;
import org.example.examendiuolalla.model.MonedaModelo;
import org.example.examendiuolalla.view.Moneda;

public class RootLayoutController {
    private MainApp mainApp = new MainApp();

    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleImagen() {
        mainApp.showImagen();
    }
}


