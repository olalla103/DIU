package com.example.gestionHotelOlallaLopez;

import com.example.gestionHotelOlallaLopez.controller.*;
import com.example.gestionHotelOlallaLopez.modelo.*;
import com.example.gestionHotelOlallaLopez.modelo.repository.GestionRepository;
import com.example.gestionHotelOlallaLopez.modelo.repository.impl.GestionRepositoryImpl;
import com.example.gestionHotelOlallaLopez.util.ClaseUtilidad;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ReservaModelo reservaModelo;
    private ClaseUtilidad claseUtilidad;

    private static GestionRepository gestionRepository;

    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();

    private TableView<Cliente> tablaCliente;
    private TableView<Reserva> tablaReserva;
    private Integer i;
    private Integer iR;


    /**
     * Constructor de la clase donde añado la lista de Clientes
     */
    public MainApp() throws ExcepcionHotel {
        clienteData.addAll(addListCliente());
        reservaModelo.setNumeroReservas(listarDI().size());
        reservaModelo.setNumeroReservas2(listarD().size());
        reservaModelo.setNumeroReservas3(listarJS().size());
        reservaModelo.setNumeroReservas4(listarS().size());
    }

    /**
     * Añade a la tabla de Clientes las Clientes
     *
     * @return
     */
    public ArrayList<Cliente> addListCliente() {
        reservaModelo = new ReservaModelo();
        claseUtilidad = new ClaseUtilidad();
        gestionRepository = new GestionRepositoryImpl();
        reservaModelo.setImpl(gestionRepository);
        ArrayList<ClienteVO> listaClienteVO = new ArrayList<ClienteVO>();
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        try {
            listaClienteVO = reservaModelo.listarClientes();
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaCliente = claseUtilidad.listaCliente(listaClienteVO);
        return listaCliente;
    }

    /**
     * Añade a la tabla reservas las reservas del cliente
     *
     * @param dniC
     * @return
     */
    public ArrayList<Reserva> addListReserva(String dniC) {
        reservaModelo = new ReservaModelo();
        claseUtilidad = new ClaseUtilidad();
        gestionRepository = new GestionRepositoryImpl();
        reservaModelo.setImpl(gestionRepository);
        ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        try {
            listaReservaVO = reservaModelo.listarReservas(dniC);
        } catch (ExcepcionHotel e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Reservas");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva = claseUtilidad.listaReserva(listaReservaVO);
        return listaReserva;
    }

    public ObservableList<Cliente> getclienteData() {
        return clienteData;
    }

    public void setclienteData(ObservableList<Cliente> clienteData) {
        this.clienteData = clienteData;
    }

    public ObservableList<Reserva> getReservaData() {
        return reservaData;
    }

    public void setReservaData(ObservableList<Reserva> reservaData) {
        this.reservaData = reservaData;
    }

    public static void main(String[] args) throws ExcepcionHotel {
        launch(args);
    }


    /**
     * Metodo Start
     *
     * @param primaryStage Escenario principal
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Reserva de hoteles");

        initRootLayout();

        showReservaOverview();
    }

    /**
     * Inicio del root layout
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            // FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/RootLayout.fxml"));
            //  FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
            // loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootLayoutController controller = loader.getController();
            controller.setreservaModelo(reservaModelo);
            controller.setclaseUtilidad(claseUtilidad);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter de i de la tabla Cliente
     *
     * @param i
     */
    public void setI(Integer i) {
        this.i = i;
    }

    /**
     * Setter de la tabla de Cliente
     *
     * @param tablaCliente
     */
    public void setTablaCliente(TableView<Cliente> tablaCliente) {
        this.tablaCliente = tablaCliente;
    }

    /**
     * Getter de tabla Cliente
     *
     * @return
     */
    public TableView<Cliente> getTablaCliente() {
        return tablaCliente;
    }

    /**
     * Getter de i de la Cliente
     *
     * @return
     */
    public Integer getI() {
        return i;
    }

    /**
     * Getter de  la tabla de reserva
     *
     * @return
     */
    public TableView<Reserva> getTablaReserva() {
        return tablaReserva;
    }

    /**
     * Setter tabla de reserva
     *
     * @param tablaReserva
     */
    public void setTablaReserva(TableView<Reserva> tablaReserva) {
        this.tablaReserva = tablaReserva;
    }

    /**
     * Getter de la i de la tabla reserva
     *
     * @return
     */
    public Integer getiR() {
        return iR;
    }

    /**
     * Setter de la i de la tabla de reserva
     *
     * @param iR
     */
    public void setiR(Integer iR) {
        this.iR = iR;
    }


    /**
     * Enseña el fxml de Reserva
     */
    public void showReservaOverview() {
        try {
            // Load cliente overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/ReservaOverView.fxml"));
            AnchorPane clienteOverview = (AnchorPane) loader.load();

            // Set cliente overview into the center of root layout.
            rootLayout.setCenter(clienteOverview);

            // Give the controller access to the main app.
            ReservaOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enseña el crear editar Cliente
     *
     * @param cliente
     * @return
     */
    public boolean showclienteEditDialog(Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/CrearCliente.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Crear/Editar Cliente");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cliente into the controller.
            CrearClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setreservaModelo(reservaModelo);
            controller.setcliente(cliente);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Enseña la reserva para crear y editar
     *
     * @param reserva
     * @param cliente
     * @return
     */
    public boolean showReservaEditDialog(Reserva reserva, Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/CrearReserva.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Crear/Editar Reserva");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the cliente into the controller.
            CrearReservaController controller = loader.getController();
            controller.setreservaModelo(reservaModelo);
            controller.setCliente(cliente);
            controller.setReserva(reserva);
            controller.setDialogStage(dialogStage);


            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Enseña la busqueda de la Cliente
     */
    public void showBusquedaCliente() {
        try {
            // Load cliente overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/BusquedaCliente.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Buscar Cliente");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the cliente into the controller.
            BusquedaClienteController controller = loader.getController();
            controller.setReservaModelo(reservaModelo);
            controller.setClaseUtilidad(claseUtilidad);
            controller.setDialogStage(dialogStage);


            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showWebView() {
        try {
            // Load cliente overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/JavaDoc.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Web View");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the cliente into the controller.
            WebViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOcupacionTotal() {
        try {
            // Load cliente overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/PorcentajeDeOcupacionTotal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ocupacion total");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the cliente into the controller.
            OcupacionTotalController controller = loader.getController();
            controller.setReservaData(listarTodo());

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionHotel e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reserva> listarTodo() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        try {
            listaReservaVO = reservaModelo.listarReservasTodas();
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva = claseUtilidad.listaReserva(listaReservaVO);
        return listaReserva;
    }

    public void showOcupacionHab() {
        try {
            // Load cliente overview.
            //FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/gestionHotelOlallaLopez/OcupacionHab.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ocupacion por habitacion");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the cliente into the controller.
            OcupacionHabController controller = loader.getController();
            controller.setReservaModelo(reservaModelo);

            // controller.setProgestionRepositoryess();
            controller.setProgress();
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reserva> listarDI() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva> listaReservaFiltrada = new ArrayList<Reserva>();
        try {
            listaReservaVO = reservaModelo.listarReservasTodas();
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva = claseUtilidad.listaReserva(listaReservaVO);
        for (int indi = 0; indi < listaReserva.size(); indi++) {
            if (listaReserva.get(indi).gettipoHabitacion().equals("Doble de uso individual")) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }

    public ArrayList<Reserva> listarD() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva> listaReservaFiltrada = new ArrayList<Reserva>();
        try {
            listaReservaVO = reservaModelo.listarReservasTodas();
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva = claseUtilidad.listaReserva(listaReservaVO);
        for (int indi = 0; indi < listaReserva.size(); indi++) {
            if (listaReserva.get(indi).gettipoHabitacion().equals("Doble")) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);
                ;
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }

    public ArrayList<Reserva> listarJS() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva> listaReservaFiltrada = new ArrayList<Reserva>();
        try {
            listaReservaVO = reservaModelo.listarReservasTodas();
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva = claseUtilidad.listaReserva(listaReservaVO);
        for (int indi = 0; indi < listaReserva.size(); indi++) {
            if (listaReserva.get(indi).gettipoHabitacion().equals("Junior suite")) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);
                ;
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }

    public ArrayList<Reserva> listarS() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservaVO = new ArrayList<ReservaVO>();
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        ArrayList<Reserva> listaReservaFiltrada = new ArrayList<Reserva>();
        try {
            listaReservaVO = reservaModelo.listarReservasTodas();
        } catch (ExcepcionHotel e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las Clientes.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaReserva = claseUtilidad.listaReserva(listaReservaVO);
        for (int indi = 0; indi < listaReserva.size(); indi++) {
            if (listaReserva.get(indi).gettipoHabitacion().equals("Suite")) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaLlegada = listaReserva.get(indi).getFechaLlegada();
                LocalDate fechaFin = listaReserva.get(indi).getFechaFin();
                boolean fechaAntesDeHoy = fechaActual.isAfter(fechaLlegada)
                        || fechaActual.equals(fechaLlegada);
                boolean fechaDespuesDeHoy = fechaActual.isBefore(fechaFin);

                if (fechaAntesDeHoy && fechaDespuesDeHoy) {
                    listaReservaFiltrada.add(listaReserva.get(indi));
                }
            }
        }
        return listaReservaFiltrada;
    }
}