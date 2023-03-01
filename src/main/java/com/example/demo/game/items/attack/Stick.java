package com.example.demo.game.items.attack;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.monster.Demon;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.TypeCharacter;

public class Stick extends AttackItem{

    private LevelSelection level;

    public Stick(LevelSelection level) {
        this.setName("Storm Spirit") ;
        this.setStats(5);
        this.setDescription("Thunder stick");
        this.setSpeBonus(true);
        this.level = level;
    }
    @Override
    protected TypeCharacter getUseBy() {
        return TypeCharacter.Wizard;
    }

    @Override
    public int applySpeBonus(Monster monster) {
        int bonus = 0;
        if (this.level == LevelSelection.Hard) {
            bonus = 4;
        }
        if (monster instanceof Demon) {
            bonus += 3;
        }
        return bonus;
    }

}
