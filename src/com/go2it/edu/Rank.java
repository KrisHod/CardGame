package com.go2it.edu;

public enum Rank {
    SIX(0), SEVEN(1), EIGHT(2), NINE(3), TEN(4), JACK(5), QUEEN(6), KING(7), ACE(8);
    private final int weight;

    Rank(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

}
