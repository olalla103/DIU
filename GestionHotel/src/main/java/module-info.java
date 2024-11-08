module org.example.gestionhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires AccesoBBDDMoneda;

    opens org.example.gestionhotel to javafx.fxml;
    exports org.example.gestionhotel;
    exports org.example.gestionhotel.controller;
    opens org.example.gestionhotel.controller to javafx.fxml;
}