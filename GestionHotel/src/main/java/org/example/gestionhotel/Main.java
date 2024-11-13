package org.example.gestionhotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.gestionhotel.model.repository.ClienteRepository;
import org.example.gestionhotel.model.repository.ExcepcionHotel;
import org.example.gestionhotel.model.repository.ReservaRepository;
import org.example.gestionhotel.model.repository.impl.ClienteRepositoryImpl;
import org.example.gestionhotel.model.repository.impl.ReservaRepositoryImpl;
import org.example.gestionhotel.model.repository.impl.regimenAlojamiento;
import org.example.gestionhotel.model.repository.impl.tipoHabitacion;
import org.example.gestionhotel.model.util.ClienteVO;
import org.example.gestionhotel.model.util.ReservaVO;
import org.example.gestionhotel.view.Cliente;

import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("rootLayout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        // recuperar clientes de la base de datos
        /*ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        System.out.println("Clientes: " + clienteRepository.ObtenerListaClientes() + "\n");

        // probar métodos de reserva
        ReservaRepository reservaRepository = new ReservaRepositoryImpl();

        // añado una reserva
        // arreglar fecha
        LocalDate llegada = LocalDate.now();
        LocalDate salida = LocalDate.ofYearDay(2024, 10);

        //ReservaVO reservaVO = new ReservaVO(3, llegada, salida, tipoHabitacion.SUITE, 2, false, regimenAlojamiento.MEDIAPENSION, "12345678B");
        reservaRepository.editReserva(2);
        System.out.println(reservaRepository.ObtenerListaReservas());*/

        //  launch();
    }
}