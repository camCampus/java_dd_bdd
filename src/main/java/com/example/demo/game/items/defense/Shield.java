package com.example.demo.game.items.defense;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.TypeCharacter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Shield extends DefenseItem{

  private String name;
private Random random;


    public Shield(LevelSelection level){
        this.random = new Random();
        this.setName(selectName()) ;
        this.setStats(selectStats(level));
        this.setDescription(getDescription(this.getName()));
    }

    private String selectName() {
        int rand = random.nextInt(ShieldList.values().length);
        List<String> shieldList = new ArrayList<>();

        for (ShieldList shield : ShieldList.values()) {
            shieldList.add(shield.name());
        }
        return shieldList.get(rand);
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
        switch (shield){
            case "Wood" ->
                description = "Old shield made in wood ";

            case "Steal" ->
                description = "Nice big shield made in steal";
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
