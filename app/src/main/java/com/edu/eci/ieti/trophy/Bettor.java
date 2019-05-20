package com.edu.eci.ieti.trophy;

public class Bettor {

    private String name;
    private String bet;
    private String player_bet;
    private int image;

    public Bettor(String name, String bet, String player_bet, int image) {
        this.name = name;
        this.bet = bet;
        this.player_bet = player_bet;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getPlayer_bet() {
        return player_bet;
    }

    public void setPlayer_bet(String player_bet) {
        this.player_bet = player_bet;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
