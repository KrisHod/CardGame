package com.go2it.edu;

import java.util.Random;

public class Game {

   static public Suit chooseTrumpCard (){
        int index = new Random().nextInt(4);
        Suit trump = Suit.arrSuit[index];
        return trump;
    }

    static public Card goPlayer (Player player){
       Card card = player.getPlayerCards().get(new Random().nextInt(player.getPlayerCards().size()));
       return card;
    }

//    static public Card coverCard (Player player){
//       return card;
//    }

}
