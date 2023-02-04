package com.example.chessstars;

import com.example.chessstars.controllers.Controller;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController extends Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText(getRatings());
    }

    private String getRatings() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://www.chess.com/ratings"))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            String body = getResponse.body();

            getRatings(body);

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();

        }


        return null;
    }

    private void getRatings(String body) {
        Scanner scan = new Scanner(body);
        String line;
        ArrayList<ChessPlayer> playerList = new ArrayList<>();

        String name = null;
        int rating = -1;
        while (scan.hasNext()) {
            line = scan.nextLine();
            if (line.contains("href=\"https://www.chess.com/players/")) {
                name = scan.nextLine().trim();
            }
            if (line.contains("class=\"master-players-rating-player-rank master-players-rating-rank-active\"")) {
                rating = scan.nextInt();
            }

            if (name != null && rating != -1) {
                ChessPlayer player = new ChessPlayer(name, rating);
                playerList.add(player);
                player.setPosition(playerList.indexOf(player));

                name = null;
                rating = -1;
            }
        }

        System.out.println(playerList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}