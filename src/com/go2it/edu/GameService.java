package com.go2it.edu;

import java.util.*;

public class GameService {

    public List<Player> players = new ArrayList<>();
    public int numberOfPlayers = getNumberOfPlayers();
    public int indexOfCurrentPlayer = searchIndexOfPlayerWithLowestTrump ();
    public Suit trump = chooseTrumpCard();

    public int getNumberOfPlayers() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of players");
        return input.nextInt();
    }


    public int getRandomNumber(int bound) {
        return new Random().nextInt(bound);
    }


    public Suit chooseTrumpCard() {
        return Suit.values()[getRandomNumber(4)];
    }

    public Card getNextCard(Set<Card> setCards) {
        return setCards.iterator().next();
    }

    public void fillListPlayer(Deck deck) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(i + 1, distributeCards(deck)));
        }
    }


    public Set<Card> distributeCards(Deck deck) {
        Set<Card> localPlayerCards = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            Card card = getNextCard(deck.getCards());
            localPlayerCards.add(card);
            deck.removeCardFromDeck(card);
        }
        return localPlayerCards;
    }

    public void passTurn() {
        indexOfCurrentPlayer = indexOfCurrentPlayer < numberOfPlayers - 1 ? indexOfCurrentPlayer++ : 0;
        System.out.println();
        System.out.println("Turn of player " + players.get(indexOfCurrentPlayer).getId());
    }

    public void hasNoCards() {
        if (players.get(indexOfCurrentPlayer).getPlayerCards().isEmpty()) {
            System.out.println("Player " + players.get(indexOfCurrentPlayer).getId() + " win!!!");
            System.exit(0);
        }
    }

    public void takeCardFromDeck(Deck deck) {
        if (!deck.getCards().isEmpty()) {
            Card card = getNextCard(deck.getCards());
            players.get(indexOfCurrentPlayer).getPlayerCards().add(card);
            deck.removeCardFromDeck(card);
        }
    }

    public Card getTheLowestTrumpCard(Set<Card> cardSet) {
        Card lowestTrumpCard = new Card(trump, Rank.ACE);
        for (Card set : cardSet) {
            if (set.getSuit().equals(trump)) {
                if (set.getRank().getWeight() < lowestTrumpCard.getRank().getWeight()) {
                    lowestTrumpCard = set;
                }
            }

        }
        return lowestTrumpCard;
    }

    public int searchIndexOfPlayerWithLowestTrump (){
        Set<Card> cardsOfAllPlayers = new HashSet<>();
        for (Player player: players) {
            cardsOfAllPlayers.addAll(player.getPlayerCards());
        }
        System.out.println("Cards of all pl " +cardsOfAllPlayers);
        Card lowestTrumpCard = getTheLowestTrumpCard(cardsOfAllPlayers);
        System.out.println("lowest" + lowestTrumpCard);
        for (Player player: players) {
            if (player.getPlayerCards().contains(lowestTrumpCard)) {
                return players.indexOf(player);
            }
        }
        return 0;
    }

    public Card attackWithCard() {
        Set<Card> currentPlayerCards = players.get(indexOfCurrentPlayer).getPlayerCards();
        Card card = getNextCard(currentPlayerCards);
        System.out.println("Player number " + players.get(indexOfCurrentPlayer).getId() + " play with " + card);
        players.get(indexOfCurrentPlayer).removeCardFromPlayerCards(card);
        System.out.println(players.get(indexOfCurrentPlayer));
        return card;
    }
//
//    public static void coverCard() {
//        Card cardToCover = ();
//        Suit suitOfCardToCover = cardToCover.getSuit();
//        int weightOfCardToCover = cardToCover.getRank().getWeight();
//
//        passTurn();
//
//        List<Card> currentPlayerCards = players.get(indexOfCurrentPlayer).getPlayerCards();
//        ListIterator<Card> iterator = currentPlayerCards.listIterator();
//
//        if (suitOfCardToCover.equals(trump)) {
//            for (int i = 0; i < currentPlayerCards.size(); i++) {
//                if (weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight() && currentPlayerCards.get(i).getSuit().equals(trump)) {
//                    System.out.println("Player cover trump card with " + currentPlayerCards.get(i));
//
//                    while (iterator.hasNext()){
//                        if(currentPlayerCards.indexOf(iterator.next()) == i){
//                            iterator.remove();
//                            break;
//                        }
//                    }
//                    takeCardFromDeck();
//                    hasNoCards();
//                    return;
//                }
//            }
//        } else {
//            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a bigger card of the same suit
//                if (suitOfCardToCover.equals(currentPlayerCards.get(i).getSuit())
//                        && weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight()) {
//                    System.out.println("Player cover card with " + currentPlayerCards.get(i));
//
//                    while (iterator.hasNext()){
//                        if(currentPlayerCards.indexOf(iterator.next()) == i){
//                            iterator.remove();
//                            break;
//                        }
//                    }
//                    takeCardFromDeck();
//                    hasNoCards();
//                    return;
//                }
//            }
//            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a trump
//                if (currentPlayerCards.get(i).getSuit().equals(trump)) {
//                    System.out.println("Player cover card with trump " + currentPlayerCards.get(i));
//
//                    while (iterator.hasNext()){
//                        if(currentPlayerCards.indexOf(iterator.next()) == i){
//                            iterator.remove();
//                            break;
//                        }
//                    }
//                    takeCardFromDeck();
//                    hasNoCards();
//                    return;
//                }
//            }
//        }
//        currentPlayerCards.add(cardToCover);
//        System.out.println("Player takes the card");
//        passTurn();
//    }
//
//    public static void playGame() {
//        System.out.println("Trump is " + trump);
//        Deck deck = new Deck(36);
//
//        fillListPlayer();
//        do {
//            coverCard();
//        } while (true);
//    }
}
