module com.example.chessstars {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.chessstars to javafx.fxml, com.google.gson;
    exports com.example.chessstars.controllers;
    exports com.example.chessstars;
    opens com.example.chessstars.controllers to com.google.gson, javafx.fxml;
    exports com.example.chessstars.model;
    opens com.example.chessstars.model to com.google.gson, javafx.fxml;
}