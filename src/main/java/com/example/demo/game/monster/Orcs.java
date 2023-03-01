package com.example.demo.game.monster;

import com.example.demo.game.board.LevelSelection;

public class Orcs extends Monster{
    public Orcs(LevelSelection level) {
        super(level);
    }

    @Override
    public int setPower(LevelSelection level) {
        int power = (level == LevelSelection.Easy)? 10: 20;
        return power;
    }

    @Override
    public int setLife(LevelSelection level) {
        int life = (level == LevelSelection.Easy)? 6: 16;
        return life;
    }
}
