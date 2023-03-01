package com.example.demo.game.items.attack;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.monster.Dragon;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.TypeCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bow extends AttackItem {

    private Random random;
    private LevelSelection level;
    public Bow(LevelSelection level) {
        this.random = new Random();
        this.setName(selectName()) ;
        this.setStats(4);
        this.setDescription(getDescription(this.getName()));
        this.setSpeBonus(true);
        this.level = level;
    }


    public int checkStatsCondition(Monster monster) {
        int bonus = 0;
        if (monster instanceof Dragon) {
            bonus = 2;
        }
        return bonus;
    }

    private String selectName() {
        int val = BowList.values().length;
        int rand = random.nextInt(val);
        List<String> bowlList = new ArrayList<>();

        for (BowList bow : BowList.values()) {
            bowlList.add(bow.name());
        }
        return bowlList.get(rand);
    }


    public String getDescription(String bow) {
        String description = "";

        switch (bow) {
            case "Fury" -> description = "An epic old elf bow";
            case "Cyclone" -> description = "Short bow made in hard wood";
        }
        return description;
    }
    @Override
    protected TypeCharacter getUseBy() {
        return TypeCharacter.Warrior;
    }

    @Override
    public int applySpeBonus(Monster monster) {
        int bonus = 0;
        if (this.level == LevelSelection.Hard) {
            bonus = 4;
        }
        if (monster instanceof Dragon) {
            bonus += 2;
        }
        return bonus;
    }
}
