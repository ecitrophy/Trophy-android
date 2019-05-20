package com.edu.eci.ieti.trophy;

public class BetCards {

    private String name;
    private String game;
    private String amountBettors;
    private String minimumBet;
    private int image;

    public BetCards(String name, String game, String amountBettors, String minimumBet, int image) {
        this.name = name;
        this.game = game;
        this.amountBettors = amountBettors;
        this.minimumBet = minimumBet;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getAmountBettors() {
        return amountBettors;
    }

    public void setAmountBettors(String amountBettors) {
        this.amountBettors = amountBettors;
    }

    public String getMinimumBet() {
        return minimumBet;
    }

    public void setMinimumBet(String minimumBet) {
        this.minimumBet = minimumBet;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
