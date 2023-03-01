package com.example.demo.game.dice;

import java.util.Random;

public class RollDice {
    private Random random;
    private int dice;

    public RollDice() {
        this.random = new Random();
        this.dice = rollDice();
    }

    public int rollDice() {
        int rand = this.random.nextInt(6);
        rand++;
        return rand;
    }
    public int getDice() {
        return dice;
    }
}
