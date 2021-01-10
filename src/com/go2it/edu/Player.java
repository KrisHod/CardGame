package com.go2it.edu;

import java.util.Set;

public class Player {
    private int id;
    private Set playerCards;

    public Player(int id, Set playerCards) {
        this.id = id;
        this.playerCards = playerCards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(Set playerCards) {
        this.playerCards = playerCards;
    }

    @Override
    public String toString() {
        return "Player id=" + id +
                ", playerCards=" + playerCards +
                '}';
    }
}
