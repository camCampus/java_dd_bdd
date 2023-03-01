package com.example.demo.game.monster;

import com.example.demo.game.board.LevelSelection;

import java.util.Random;

public abstract class Monster {
    private int life;
    private int power;
    private Random random;

    protected Monster( LevelSelection level) {
        this.random = new Random();
        this.life = setPower(level);
        this.power = setLife(level);
    }

    public abstract int setPower(LevelSelection level);

    public abstract int setLife(LevelSelection level);

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public String toString() {
        String monster = null;
        if (this instanceof Dragon){monster = "Dragon";};
        if (this instanceof Gobelin){monster = "Gobelin";};
        if (this instanceof Sorcerer){monster = "Sorcerer";};
        if (this instanceof Demon){monster = "Demon";};
        if (this instanceof Orcs){monster = "Orcs";};
        return  "Tadam a monster appears in front of you !! " + "\n" +
                monster + "\n" + "life: " + getLife()
                + "\n" + "power: " + getPower();
    }

   // public abstract void runAttack();

}
