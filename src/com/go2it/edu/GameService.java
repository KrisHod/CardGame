package com.go2it.edu;

import java.util.*;

public class GameService {

    public List<Player> players = new ArrayList<>();
    public int numberOfPlayers = getNumberOfPlayers();
    public int indexOfCurrentPlayer = searchIndexOfPlayerWithLowestTrump();
    Set<Card> currentPlayerCards = players.get(indexOfCurrentPlayer).getPlayerCards();
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

    public boolean hasCards() {
        boolean res = true;
        for (Player player : players) {
            if (player.getPlayerCards().isEmpty()) {
                System.out.println("Player " + player.getId() + " win!!!");
                res = false;
            }
        }
        return res;
    }

    public void takeCardFromDeck(Deck deck) {
        if (!deck.getCards().isEmpty()) {
            for (Player player : players) {
                if (player.getPlayerCards().size() < 6) {
                    Card cardToTake = getNextCard(deck.getCards());
                    player.getPlayerCards().add(cardToTake);
                    deck.removeCardFromDeck(cardToTake);
                }
            }
        }
    }

    public Card getTheLowestTrumpCard(Set<Card> cardSet) {
        Card lowestTrumpCard = new Card(trump, Rank.ACE);
        for (Card cardFromSet : cardSet) {
            if (cardFromSet.getSuit().equals(trump)) {
                if (cardFromSet.getRank().getWeight() < lowestTrumpCard.getRank().getWeight()) {
                    lowestTrumpCard = cardFromSet;
                }
            }

        }
        return lowestTrumpCard;
    }

    public int searchIndexOfPlayerWithLowestTrump() {
        Set<Card> cardsOfAllPlayers = new HashSet<>();
        for (Player player : players) {
            cardsOfAllPlayers.addAll(player.getPlayerCards());
        }
        System.out.println("Cards of all pl " + cardsOfAllPlayers);
        Card lowestTrumpCard = getTheLowestTrumpCard(cardsOfAllPlayers);
        System.out.println("lowest" + lowestTrumpCard);
        for (Player player : players) {
            if (player.getPlayerCards().contains(lowestTrumpCard)) {
                return players.indexOf(player);
            }
        }
        return 0;
    }

    public void attackAndCoverIfPossible() {
        Card cardToCover = getNextCard(currentPlayerCards);
        System.out.println("Player number " + players.get(indexOfCurrentPlayer).getId() + " play with " + cardToCover);
        players.get(indexOfCurrentPlayer).removeCardFromPlayerCards(cardToCover);

        passTurn();
        if (!(ifCardCanBeCoveredWithSameSuit(cardToCover) || hasTrumpToCover(cardToCover))) { //if player cannot defend, he takes the card
            currentPlayerCards.add(cardToCover);
            passTurn();
        }
    }


    public boolean ifCardCanBeCoveredWithSameSuit(Card card) {
        boolean res = false;

        Suit suitOfCardToCover = card.getSuit();
        int weightOfCardToCover = card.getRank().getWeight();
        //looking for a bigger card of the same suit
        for (Card cardFromSet : currentPlayerCards) {
            if (suitOfCardToCover.equals(cardFromSet.getSuit())
                    && weightOfCardToCover < cardFromSet.getRank().getWeight()) {
                System.out.println("Player cover card with " + cardFromSet);
                players.get(indexOfCurrentPlayer).removeCardFromPlayerCards(cardFromSet);
                res = true;
                break;
            }
        }
        return res;
    }


    public boolean hasTrumpToCover(Card card) {
        boolean res = false;
        if (card.getSuit().equals(trump)) {
            for (Card cardFromSet : currentPlayerCards) {
                if (card.getRank().getWeight() < cardFromSet.getRank().getWeight() && cardFromSet.getSuit().equals(trump)) {
                    System.out.println("Player cover trump card with " + cardFromSet);
                    players.get(indexOfCurrentPlayer).removeCardFromPlayerCards(cardFromSet);
                    res = true;
                    break;
                }
            }
        } else {
            for (Card cardFromSet : currentPlayerCards) {
                if (cardFromSet.getSuit().equals(trump)) {
                    System.out.println("Player cover card with trump " + cardFromSet);
                    players.get(indexOfCurrentPlayer).removeCardFromPlayerCards(cardFromSet);
                    res = true;
                    break;
                }
            }
        }
        return res;
    }


    public void playGame() {
        Deck deck = new Deck(36);
        fillListPlayer(deck);
        System.out.println("Trump is " + trump);
        System.out.println("Player " + searchIndexOfPlayerWithLowestTrump() + " attack first");
        while (hasCards()){
            attackAndCoverIfPossible();
            takeCardFromDeck(deck);
        }
    }

//    public void coverCard(Card card) {
//        Suit suitOfCardToCover = card.getSuit();
//        int weightOfCardToCover = card.getRank().getWeight();
//
//
//        List<Card> currentPlayerCards = players.get(indexOfCurrentPlayer).getPlayerCards();
//        ListIterator<Card> iterator = currentPlayerCards.listIterator();
//
//        if (suitOfCardToCover.equals(trump)) {
//            for (int i = 0; i < currentPlayerCards.size(); i++) {
//                if (weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight() && currentPlayerCards.get(i).getSuit().equals(trump)) {
//                    System.out.println("Player cover trump card with " + currentPlayerCards.get(i));
//
//                    while (iterator.hasNext()) {
//                        if (currentPlayerCards.indexOf(iterator.next()) == i) {
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
////            for (int i = 0; i < currentPlayerCards.size(); i++) { //looking for a bigger card of the same suit
////                if (suitOfCardToCover.equals(currentPlayerCards.get(i).getSuit())
////                        && weightOfCardToCover < currentPlayerCards.get(i).getRank().getWeight()) {
////                    System.out.println("Player cover card with " + currentPlayerCards.get(i));
//
//            while (iterator.hasNext()) {
//                if (currentPlayerCards.indexOf(iterator.next()) == i) {
//                    iterator.remove();
//                    break;
//                }
//            }
//            takeCardFromDeck();
//            hasNoCards();
//            return;
//        }
//    }
//            for(
//    int i = 0; i<currentPlayerCards.size();i++)
//
//    { //looking for a trump
//        if (currentPlayerCards.get(i).getSuit().equals(trump)) {
//            System.out.println("Player cover card with trump " + currentPlayerCards.get(i));
//
//            while (iterator.hasNext()) {
//                if (currentPlayerCards.indexOf(iterator.next()) == i) {
//                    iterator.remove();
//                    break;
//                }
//            }
//            takeCardFromDeck();
//            hasNoCards();
//            return;
//        }
//    }
//}
//        currentPlayerCards.add(cardToCover);
//                System.out.println("Player takes the card");
//                passTurn();
//                }
////
////    public static void playGame() {
////
////        Deck deck = new Deck(36);
////
////        fillListPlayer();
////        do {
////            coverCard();
////        } while (true);
////    }
}
