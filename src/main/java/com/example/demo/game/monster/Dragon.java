package com.example.demo.game.monster;

import com.example.demo.game.board.LevelSelection;

public class Dragon extends Monster{
    public Dragon(LevelSelection level) {
        super(level);
    }


    @Override
    public int setPower(LevelSelection level) {
        int power = (level == LevelSelection.Easy)? 4: 8;
        return power;
    }

    @Override
    public int setLife(LevelSelection level) {
        int life = (level == LevelSelection.Easy)? 15: 22;
        return life;
    }
}
