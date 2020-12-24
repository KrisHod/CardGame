package com.go2it.edu;

import static com.go2it.edu.Card.createDeck;
import static com.go2it.edu.Game.chooseTrumpCard;
import static com.go2it.edu.Game.goPlayer;

public class Main {
    public static void main(String[] args) {
        createDeck();
        System.out.println(chooseTrumpCard());
        Player player1 = new Player();
        Player player2 = new Player();
        System.out.println(player1);
        System.out.println(player2);

        System.out.println(goPlayer(player1));



    }
}
