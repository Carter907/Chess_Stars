package com.example.chessstars.controllers;

import com.example.chessstars.model.ChessPlayer;
import com.example.chessstars.model.RatingScraper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChessController extends Controller {

    @FXML
    public TextArea outputText;

    @FXML
    protected void getPlayersAction() {


        RatingScraper scraper = new RatingScraper();

        ArrayList<ChessPlayer> players = scraper.scrapeChessRatings();

        outputText.setText(scraper.getPlayersJson());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}