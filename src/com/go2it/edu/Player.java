package com.go2it.edu;

import java.util.Objects;
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

    public void removeCardFromPlayerCards(Card card){
        playerCards.remove(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId() == player.getId() && Objects.equals(getPlayerCards(), player.getPlayerCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlayerCards());
    }

    @Override
    public String toString() {
        return "Player id=" + id +
                ", playerCards=" + playerCards +
                '}';
    }
}
