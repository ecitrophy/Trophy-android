package com.edu.eci.ieti.trophy;

public class betCards {

    private String nameBet;
    private String nameGame;
    private String amountBettors;
    private String bet;
    private int image;

    public betCards(String nameBet, String nameGame, String amountBettors, String bet, int image) {
        this.nameBet = nameBet;
        this.nameGame = nameGame;
        this.amountBettors = amountBettors;
        this.bet = bet;
        this.image = image;
    }

    public String getNameBet() {
        return nameBet;
    }

    public void setNameBet(String nameBet) {
        this.nameBet = nameBet;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public String getAmountBettors() {
        return amountBettors;
    }

    public void setAmountBettors(String amountBettors) {
        this.amountBettors = amountBettors;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
