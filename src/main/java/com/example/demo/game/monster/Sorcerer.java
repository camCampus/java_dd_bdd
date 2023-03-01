package com.example.demo.game.monster;

import com.example.demo.game.board.LevelSelection;

public class Sorcerer extends Monster{
    public Sorcerer (LevelSelection level) {
        super(level);
    }
    @Override
    public int setPower(LevelSelection level) {
        int power = (level == LevelSelection.Easy)? 2: 15;
        return power;
    }

    @Override
    public int setLife(LevelSelection level) {
        int life = (level == LevelSelection.Easy)? 9: 20;
        return life;
    }
}
