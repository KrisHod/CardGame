package com.go2it.edu;

import java.util.*;

public class GameService {
    public static List<Card> cardArray = new ArrayList<>();
    public static List<Player> playerArray = new ArrayList<>();
    public static int numberOfPlayers = getNumberOfPlayers();
    public static int indexOfCurrentPlayer = 0;
    public static Suit trump = chooseTrumpCard();

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
        return Suit.values()[getRandomNumber(4)];
    }

    public static void shuffle() {
        Collections.shuffle(cardArray);
    }

    public static List<Player> fillListPlayer() {
        for (int i = 0; i < numberOfPlayers; i++) {
            playerArray.add(new Player(i + 1, distributeCards()));
        }
        return playerArray;
    }


    public static List<Card> distributeCards() {
        List<Card> localPlayerCards = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            localPlayerCards.add(cardArray.get(i));
        }
        Iterator<Card> iterator = cardArray.iterator();
        for (int i = 0; iterator.hasNext() && i < 6; i++) {
            iterator.next();
            iterator.remove();
        }
        return localPlayerCards;
    }

    public static void passTurn() {
        if (indexOfCurrentPlayer < numberOfPlayers-1) {
            indexOfCurrentPlayer++;
        } else {
            indexOfCurrentPlayer = 0;
        }
        System.out.println();
        System.out.println("Turn of player " + playerArray.get(indexOfCurrentPlayer).getId());
    }

    public static void hasNoCards() {
        if (playerArray.get(indexOfCurrentPlayer).getPlayerCards().isEmpty()) {
            System.out.println("Player " + playerArray.get(indexOfCurrentPlayer).getId() + " win!!!");
            System.exit(0);
        }
    }

    public static void takeCardFromDeck() {
        if (!cardArray.isEmpty()) {
            playerArray.get(indexOfCurrentPlayer).getPlayerCards().add(cardArray.get(0));
            Iterator<Card> iterator = cardArray.iterator();
            iterator.next();
            iterator.remove();
        }
    }

    public static Card playWithCard() {
        List<Card> currentPlayerCards = playerArray.get(indexOfCurrentPlayer).getPlayerCards();
        Card card = currentPlayerCards.get(0);
        Iterator<Card> iterator = currentPlayerCards.iterator();

        System.out.println("Player number " + playerArray.get(indexOfCurrentPlayer).getId() + " play with " + card);

        iterator.next();
        iterator.remove();

        takeCardFromDeck();
        hasNoCards();
        return card;
    }

    public static List<Card> coverCard() {
        Card cardToCover = playWithCard();
        Suit suitOfCardToCover = cardToCover.getSuit();
        int weightOfCardToCover = cardToCover.getRank().getWeight();

        passTurn();

        List<Card> currentPlayerCards = playerArray.get(indexOfCurrentPlayer).getPlayerCards();
        ListIterator<Card> iterator = currentPlayerCards.listIterator();

        if (suitOfCardToCover.equals(trump)) {
            for (int i = 0; i < currentPlayerCards.size(); i++) {
                if (weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight() && currentPlayerCards.get(i).getSuit().equals(trump)) {
                    System.out.println("Player cover trump card with " + currentPlayerCards.get(i));

                    while (iterator.hasNext()){
                        if(currentPlayerCards.indexOf(iterator.next()) == i){
                            iterator.remove();
                            break;
                        }
                    }
                    takeCardFromDeck();
                    hasNoCards();
                    return currentPlayerCards;
                }
            }
        } else {
            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a bigger card of the same suit
                if (suitOfCardToCover.equals(currentPlayerCards.get(i).getSuit())
                        && weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight()) {
                    System.out.println("Player cover card with " + currentPlayerCards.get(i));

                    while (iterator.hasNext()){
                        if(currentPlayerCards.indexOf(iterator.next()) == i){
                            iterator.remove();
                            break;
                        }
                    }
                    takeCardFromDeck();
                    hasNoCards();
                    return currentPlayerCards;
                }
            }
            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a trump
                if (currentPlayerCards.get(i).getSuit().equals(trump)) {
                    System.out.println("Player cover card with trump " + currentPlayerCards.get(i));

                    while (iterator.hasNext()){
                        if(currentPlayerCards.indexOf(iterator.next()) == i){
                            iterator.remove();
                            break;
                        }
                    }
                    takeCardFromDeck();
                    hasNoCards();
                    return currentPlayerCards;
                }
            }
        }
        currentPlayerCards.add(cardToCover);
        System.out.println("Player takes the card");
        passTurn();
        return currentPlayerCards;
    }

    public static void playGame() {
        System.out.println("Trump is " + trump);
        Deck deck = new Deck(36);
        fillDeck(deck);
        shuffle();
        fillListPlayer();
        do {
            coverCard();
        } while (true);
    }
}
