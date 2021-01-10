package com.go2it.edu;


import static com.go2it.edu.GameService.*;

public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Deck deck = new Deck(36);
        gameService.playGame();

    }
}
