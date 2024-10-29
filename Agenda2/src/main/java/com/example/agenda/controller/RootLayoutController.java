package com.example.agenda.controller;


import com.example.agenda.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleShowPersonOverview() {
        mainApp.showPersonOverview(); // Muestra la vista de personas en la interfaz
    }

}