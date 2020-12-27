package com.go2it.edu;

import java.util.*;

public class GameService {
    public static List<Card> cardArray = new ArrayList<>();
    public static List<Player> playerArray = new ArrayList<>();
    public static int numberOfPlayers = getNumberOfPlayers();
    public static int indexOfCurrentPlayer = 0;

    public static int getNumberOfPlayers() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of players");
        return input.nextInt();
    }

    public static List<Card> fillDeck(Deck deck) {
        int i = 0;
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                if (cardArray.size() < deck.getNumCards()) {
                    cardArray.add(new Card(s, r));
                }
                i++;
            }
        }
        return cardArray;
    }

    public static int getRandomNumber(int bound) {
        return new Random().nextInt(bound);
    }


    public static Suit chooseTrumpCard() {
        Suit trump = Suit.values()[getRandomNumber(4)];
        System.out.println("Trump is " + trump);
        return trump;
    }

    public static void shuffle() {
        Collections.shuffle(cardArray);
    }

    public static List<Player> fillListPlayer(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            playerArray.add(new Player(i + 1, distributeCards()));
        }
        return playerArray;
    }

//    public static List<Card> sortCardsByRank(){
//        List<Card> cards = playerArray.get(indexOfCurrentPlayer).getPlayerCards();
//        List<Integer> arrWeightOfRank = new ArrayList<>();
//        for (Card arr: cards) {
//
//        }
//        return cards;
//    }

    public static List<Card> distributeCards() {
        List<Card> localPlayerCards = cardArray.subList(0, 6);
        cardArray = cardArray.subList(6, cardArray.size());
        return localPlayerCards;
    }

    public static void passTurn() {
        if (indexOfCurrentPlayer < numberOfPlayers) {
            indexOfCurrentPlayer++;
        } else {
            indexOfCurrentPlayer = 0;
        }
        System.out.println("Turn of player " + indexOfCurrentPlayer);
    }

    public static Card playWithCard() {
        passTurn();
        Card card = playerArray.get(indexOfCurrentPlayer).getPlayerCards().get(0);
        System.out.println("Player number " + indexOfCurrentPlayer+ " play with " + card);
      //  playerArray.get(indexOfCurrentPlayer).getPlayerCards().remove(0);
        return card;
    }

    public static List<Card> coverCard() {
        Card cardToCover = playWithCard();
        Suit suitOfCardToCover = cardToCover.getSuit();
        int weightOfCardToCover = cardToCover.getRank().getWeight();

        passTurn();

        List<Card> currentPlayerCards = playerArray.get(indexOfCurrentPlayer).getPlayerCards();

        if (suitOfCardToCover.equals(Suit.CLUBS)) {
            for (int i = 0; i < currentPlayerCards.size(); i++) {
                if (weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight()) {
                    System.out.println(currentPlayerCards.get(i));
                    currentPlayerCards.remove(i);
                    return currentPlayerCards;
                }
            }
        } else {
            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a bigger card of the same suit
                if (suitOfCardToCover.equals(currentPlayerCards.get(i).getSuit())
                        && weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight()) {
                    System.out.println(currentPlayerCards.get(i));
                    currentPlayerCards.remove(i);
                    return currentPlayerCards;
                }
            }
            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a trump
                if (currentPlayerCards.get(i).getSuit().equals(Suit.CLUBS)) {
                    System.out.println(currentPlayerCards.get(i));
                    currentPlayerCards.remove(i);
                    return currentPlayerCards;
                }
            }
        }
        currentPlayerCards.add(cardToCover);
        return currentPlayerCards;
    }


}
