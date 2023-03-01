package com.example.demo.game.items.defense;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.TypeCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MagicShield extends DefenseItem {


    private Random random;
    public MagicShield(LevelSelection level) {
        this.random = new Random();
        this.setName(selectName()) ;
        this.setStats(selectStats(level));
        this.setDescription(getDescription(this.getName()));
    }

    private String selectName() {
        int val = MagicShieldList.values().length;
        int rand = random.nextInt(val);
        List<String> magicShieldList = new ArrayList<>();

        for (MagicShieldList magicShield : MagicShieldList.values()) {
            magicShieldList.add(magicShield.name());
        }
        return magicShieldList.get(rand);
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

    public String getDescription(String shield) {
        String description = "";
        switch (shield) {
            case "FireShield" ->
                description = "Primary Magic Shield";

            case "IceShield" ->
                description = "Powerfull Ice Protection";

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
