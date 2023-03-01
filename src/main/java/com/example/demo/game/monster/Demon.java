package com.example.demo.game.monster;

import com.example.demo.game.board.LevelSelection;

public class Demon extends Monster{
    public Demon(LevelSelection level) {
        super(level);
    }

    @Override
    public int setPower(LevelSelection level) {
        int power = (level == LevelSelection.Easy)? 15: 30;
        return power;
    }

    @Override
    public int setLife(LevelSelection level) {
        int life = (level == LevelSelection.Easy)? 5: 15;
        return life;
    }
}
