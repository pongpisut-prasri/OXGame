package com.example.oxgame.model;

public class Player {
    private String name;
    private int id; // 1 = ผู้เล่น, 2 = AI

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
