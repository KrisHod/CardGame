package com.go2it.edu;

import java.util.List;

public class Player {
    private int id;
    private List<Card> playerCards;

    public Player(int id, List<Card> playerCards) {
        this.id = id;
        this.playerCards = playerCards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerCards=" + playerCards +
                '}';
    }
}
