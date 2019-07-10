package com.wildrunners.wildrunners.entities;

public class Player {

    private String player1 = "Julien";
    private String player2 = "Sebastien";

    public Player(String player1, String player2) {
        this.setPlayer1(player1);
        this.setPlayer2(player2);
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }



}