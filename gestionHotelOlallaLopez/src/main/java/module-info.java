module com.example.gestionHotelOlallaLopez {
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
    requires jdk.compiler;

    opens com.example.gestionHotelOlallaLopez to javafx.fxml;
    exports com.example.gestionHotelOlallaLopez;
    exports com.example.gestionHotelOlallaLopez.controller;
    opens com.example.gestionHotelOlallaLopez.controller to javafx.fxml;
}