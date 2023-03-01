package com.example.demo.game.items.attack;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.TypeCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sword extends AttackItem {


    private Random random;

    public Sword(LevelSelection level){
        this.random = new Random();
        this.setName(selectName()) ;
        this.setStats(selectStats(level));
        this.setDescription(getDescription(this.getName()));
    }



    private String selectName() {
        int val = SwordList.values().length;
        int rand = random.nextInt(val);
        List<String> swordList = new ArrayList<>();

        for (SwordList sword : SwordList.values()) {
            swordList.add(sword.name());
        }
        return swordList.get(rand);
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

    public String getDescription(String sword) {
        String description = "";
        switch (sword){

            case "Ghostwalker" ->
                description = "The Ghostwalker is a warrior's sword, feared by all who cross its path. Its sleek and mysterious design is matched only by its unparalleled sharpness and strength.";

            case "Extinction" ->
                description = "The Extinction sword is a weapon of legend, said to hold the power to bring an end to all that stands in its path. \n Its gleaming blade, forged from the finest of metals, is etched with runes of immense power.";

        }
        return description;
    }

    @Override
    protected TypeCharacter getUseBy() {
        return TypeCharacter.Warrior;
    }


    @Override
    public int applySpeBonus(Monster monster) {
        return 0;
    }
}
