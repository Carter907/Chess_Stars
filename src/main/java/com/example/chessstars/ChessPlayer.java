package com.example.chessstars;

import com.google.gson.GsonBuilder;

public class ChessPlayer {

    private String name;
    private int rating;
    private int position;
    public ChessPlayer(String name, int rating) {

        this.name = name;
        this.rating = rating;
        position = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
