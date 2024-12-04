package com.example.gestionHotelOlallaLopez.controller;

import com.example.gestionHotelOlallaLopez.modelo.ReservaModelo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;

public class OcupacionHabController {
    @FXML
    private ProgressIndicator progressDI;
    @FXML
    private ProgressIndicator progressD;
    @FXML
    private ProgressIndicator progressJS;
    @FXML
    private ProgressIndicator progressS;

    private IntegerProperty ocupDI = new SimpleIntegerProperty();
    private IntegerProperty ocupD = new SimpleIntegerProperty();
    private IntegerProperty ocupJS = new SimpleIntegerProperty();
    private IntegerProperty ocupS = new SimpleIntegerProperty();

    private ReservaModelo reservaModelo;

    public void setReservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;
    }

    public void setProgress() {
        this.ocupDI.bind(reservaModelo.getNumeroReservasProperty());
        this.progressDI.setProgress((double) ocupDI.get() / 20);
        ocupDI.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressDI.setProgress((double) ocupDI.get() / 20);
            }
        });
        this.ocupD.bind(reservaModelo.getNumeroReservasProperty2());
        this.progressD.setProgress((double) ocupD.get() / 80);
        ocupD.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressD.setProgress((double) ocupD.get() / 80);
            }
        });
        this.ocupJS.bind(reservaModelo.getNumeroReservasProperty3());
        this.progressJS.setProgress((double) ocupJS.get() / 15);
        ocupJS.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressJS.setProgress((double) ocupJS.get() / 15);
            }
        });
        this.ocupS.bind(reservaModelo.getNumeroReservasProperty4());
        this.progressS.setProgress((double) ocupS.get() / 5);
        ocupS.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                progressS.setProgress((double) ocupS.get() / 5);
            }
        });
    }

    @FXML
    private void initialize() {
    }
}