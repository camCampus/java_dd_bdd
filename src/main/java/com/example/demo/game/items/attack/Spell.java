package com.example.demo.game.items.attack;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.TypeCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spell extends AttackItem{
    private Random random;

    public Spell(LevelSelection level) {
        this.random = new Random();
        this.setName(selectName()) ;
        this.setStats(selectStats(level));
        this.setDescription(getDescription(this.getName()));
    }



    private String selectName() {
        int val = SpellList.values().length;
        int rand = random.nextInt(val);
        List<String> spellList = new ArrayList<>();

        for (SpellList spell : SpellList.values()) {
            spellList.add(spell.name());
        }
        return spellList.get(rand);
    }

    private int selectStats(LevelSelection level) {
        int easy = (int)(Math.random()*(6-1+1)+1);
        int hard = (int)(Math.random()*(11-6+1)+6);
        int stats;
        if (level == LevelSelection.Easy) {
            stats = easy;
        } else {
            stats = hard;
        }
        return stats;
    }
    public String getDescription(String spell) {
        String description = "";

        switch (spell) {
            case "IceArrow" -> description = "Ice Arrow hurt ans slowdown the enemy";
            case "FireBlast" -> description = "Ball of fire, hurt and burn the enemy";
        }
        return description;
    }


    @Override
    protected TypeCharacter getUseBy() {
        return TypeCharacter.Wizard;
    }

    @Override
    public int applySpeBonus(Monster monster) {
        return 0;
    }
}
