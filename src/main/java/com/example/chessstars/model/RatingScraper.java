package com.example.chessstars.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class RatingScraper {
    private ArrayList<ChessPlayer> players;
    private final String site;
    private final Gson gson;

    {
        site = "https://www.chess.com/ratings";
        gson = new GsonBuilder().setPrettyPrinting().create();
        players = new ArrayList<>();
    }

    public RatingScraper() {
    }

    public ArrayList<ChessPlayer> scrapeChessRatings() {
        String body = getPageHTML();
        players = getChessPlayers(body);

        return players;
    }

    private String getPageHTML() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(site))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return getResponse.body();

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();

        }


        return null;
    }

    private ArrayList<ChessPlayer> getChessPlayers(String body) {
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
        return playerList;


    }

    public String getPlayersJson() {
        return gson.toJson(players);
    }
}
