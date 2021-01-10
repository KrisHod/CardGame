package com.go2it.edu;


import static com.go2it.edu.GameService.*;

public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Deck deck = new Deck(36);
        System.out.println(deck);
        System.out.println();
        gameService.fillListPlayer(deck);
        System.out.println(gameService.players);
        System.out.println();

        System.out.println(gameService.players);
        System.out.println();
        System.out.println(deck);

        System.out.println(gameService.trump);

        System.out.println(gameService.getTheLowestTrumpCard(gameService.players.get(0).getPlayerCards()));
        System.out.println(gameService.getTheLowestTrumpCard(gameService.players.get(1).getPlayerCards()));

        System.out.println(gameService.searchIndexOfPlayerWithLowestTrump());

        System.out.println(gameService.attackWithCard());

    }
}
