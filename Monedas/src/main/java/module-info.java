module org.example.monedas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires AccesoBBDDMoneda;

    opens org.example.monedas to javafx.fxml;
    exports org.example.monedas;
    exports org.example.monedas.controller;
    opens org.example.monedas.controller to javafx.fxml;
}