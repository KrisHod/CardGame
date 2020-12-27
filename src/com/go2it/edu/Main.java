package com.go2it.edu;


import static com.go2it.edu.GameService.*;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck(36);
        fillDeck(deck);
        shuffle();
        fillListPlayer(4);
        System.out.println(playerArray.get(0));
        System.out.println(playerArray.get(1));
        System.out.println(playerArray.get(2));
        System.out.println(playerArray.get(3));

       coverCard();





    }
}
