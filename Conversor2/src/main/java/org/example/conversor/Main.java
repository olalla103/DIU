package org.example.conversor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.MonedaRepositoryImpl;

import java.util.Iterator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ExcepcionMoneda {
        try {
            MonedaRepositoryImpl monedaRepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("prueba", 1.2F);
            monedaRepositoryImpl.addMoneda(monedaPrueba);
        } catch (ExcepcionMoneda e) {
            throw new ExcepcionMoneda("Petó bien fuelte");
        }
    }
}