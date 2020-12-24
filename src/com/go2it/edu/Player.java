package com.go2it.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private List<Card> playerCards =new ArrayList();

    public Player() {
        name = enterName();
        playerCards = getFirst6Cards();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", playerCards=" + playerCards +
                '}';
    }

    public String enterName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name");
        setName(input.next());
        return name;
    }

    public List<Card> getFirst6Cards (){
        for (int i = 0; i < 6; i++) {
            playerCards.add(Card.getDeck()[new Random().nextInt(36)]);
        }
        return playerCards;
    }



}
