module com.example.chessstars {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.chessstars to javafx.fxml, com.google.gson;
    exports com.example.chessstars.controllers;
    exports com.example.chessstars;
}