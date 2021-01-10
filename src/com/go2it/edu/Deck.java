package com.go2it.edu;

import java.util.*;

public class Deck {
    private int numCards;
    private Set<Card> cards;

    public Deck(int numCards) {
        this.numCards = numCards;
        this.cards = new HashSet<>();
        fillDeck();
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void fillDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                if (cards.size() < getNumCards()) {
                    cards.add(new Card(s, r));
                }
            }
        }
    }

    public void removeCardFromDeck(Card card) {
        cards.remove(card);
    }


    @Override
    public String toString() {
        return "Deck{" +
                "numCards=" + numCards +
                ", cards=" + cards +
                '}';
    }
}
