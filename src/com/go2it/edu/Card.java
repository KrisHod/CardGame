package com.go2it.edu;

public class Card {
    private Suit suit;
    private Rank rank;
    static private Card[] deck = new Card[36];

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    static public Card[] getDeck() {
        return deck;
    }

    public void setDeck(Card[] deck) {
        this.deck = deck;
    }

    static public void createDeck() {
        int i = 0;
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                deck[i] = new Card(s, r);
                i++;
            }
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }

    //    Card clubsSix = new Card(Suit.CLUBS, Rank.SIX);
//    Card clubsSeven = new Card(Suit.CLUBS, Rank.SEVEN);
//    Card clubsEight = new Card(Suit.CLUBS, Rank.EIGHT);
//    Card clubsNine = new Card(Suit.CLUBS, Rank.NINE);
//    Card clubsTen = new Card(Suit.CLUBS, Rank.TEN);
//    Card clubsJack = new Card(Suit.CLUBS, Rank.JACK);
//    Card clubsQueen = new Card(Suit.CLUBS, Rank.QUEEN);
//    Card clubsKing = new Card(Suit.CLUBS, Rank.KING);
//    Card clubsAce = new Card(Suit.CLUBS, Rank.ACE);
//
//    Card diamondsSix = new Card(Suit.DIAMONDS, Rank.SIX);
//    Card diamondsSeven = new Card(Suit.DIAMONDS, Rank.SEVEN);
//    Card diamondsEight = new Card(Suit.DIAMONDS, Rank.EIGHT);
//    Card diamondsNine = new Card(Suit.DIAMONDS, Rank.NINE);
//    Card diamondsTen = new Card(Suit.DIAMONDS, Rank.TEN);
//    Card diamondsJack = new Card(Suit.DIAMONDS, Rank.JACK);
//    Card diamondsQueen = new Card(Suit.DIAMONDS, Rank.QUEEN);
//    Card diamondsKing = new Card(Suit.DIAMONDS, Rank.KING);
//    Card diamondsAce = new Card(Suit.DIAMONDS, Rank.ACE);
//
//    Card heartsSix = new Card(Suit.HEARTS, Rank.SIX);
//    Card heartsSeven = new Card(Suit.HEARTS, Rank.SEVEN);
//    Card heartsEight = new Card(Suit.HEARTS, Rank.EIGHT);
//    Card heartsNine = new Card(Suit.HEARTS, Rank.NINE);
//    Card heartsTen = new Card(Suit.HEARTS, Rank.TEN);
//    Card heartsJack = new Card(Suit.HEARTS, Rank.JACK);
//    Card heartsQueen = new Card(Suit.HEARTS, Rank.QUEEN);
//    Card heartsKing = new Card(Suit.HEARTS, Rank.KING);
//    Card heartsAce = new Card(Suit.HEARTS, Rank.ACE);
//
//    Card spadesSix = new Card(Suit.SPADES, Rank.SIX);
//    Card spadesSeven = new Card(Suit.SPADES, Rank.SEVEN);
//    Card spadesEight = new Card(Suit.SPADES, Rank.EIGHT);
//    Card spadesNine = new Card(Suit.SPADES, Rank.NINE);
//    Card spadesTen = new Card(Suit.SPADES, Rank.TEN);
//    Card spadesJack = new Card(Suit.SPADES, Rank.JACK);
//    Card spadesQueen = new Card(Suit.SPADES, Rank.QUEEN);
//    Card spadesKing = new Card(Suit.SPADES, Rank.KING);
//    Card spadesAce = new Card(Suit.SPADES, Rank.ACE);


}
