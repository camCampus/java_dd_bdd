package com.example.demo.game.perso;
import com.example.demo.game.items.attack.AttackItem;
import com.example.demo.game.items.defense.DefenseItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Perso {

    /**
     * Nom du personnage définit par l'utilisateur
     */
    private String name;

    /**
     * le type du personnage Guerrier par default ou Magicien
     */
    private TypeCharacter type;

    /**
     * La vie du personnage
     */
    private int life;

    /**
     * Les dégats du personnage
     */
    private int power;

    private AttackItem attackItem ;
    private DefenseItem defenseItem;
    private int defBonus;
    private int attackBonus;
    private boolean alive;
    private Random random;
    private int position;
    private List<AttackItem> inventory;
    private boolean thunderPotion;
    Perso(String name, TypeCharacter type) {
        this.name = name;
        this.type = type;
        this.life = randomLife(type);
        this.power = randomPower(type);
        this.attackItem = null;
        this.defenseItem = null;
        this.alive = true;
        this.defBonus = 0;
        this.attackBonus=0;
        this.thunderPotion = false;
        this.inventory = new ArrayList<>();
        this.position = 0;
    }


    public int randomLife(TypeCharacter type) {
        int rand = 0;
        switch (type) {
            case Warrior -> rand = (int)(Math.random()*(11-6+1)+6);
            case Wizard -> rand = (int)(Math.random()*(7-4+1)+4);
        }
        return rand;
    }
    public int randomPower(TypeCharacter type) {
        int rand = 0;
        switch (type) {
            case Warrior -> rand = (int)(Math.random()*(11-6+1)+6);
            case Wizard -> rand = (int)(Math.random()*(16-8+1)+8);
        }
        return rand;
    }
    public String getName() {
        return name;
    }

    public TypeCharacter getType() {
        return type;
    }

    public int getLife() {
        return life;
    }

    public int getPower() {
        return power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TypeCharacter type) {
        this.type = type;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public AttackItem getAttackItem() {
        return attackItem;
    }

    public void setAttackItem(AttackItem attackItem) {
        this.attackItem = attackItem;
        this.setAttackBonus(this.attackItem.getStats());
    }

    public DefenseItem getDefenseItem() {
        return defenseItem;
    }

    public void setDefenseItem(DefenseItem defenseItem) {
        this.defenseItem = defenseItem;
    }

    public int getDefBonus() {
        return defBonus;
    }

    public void setDefBonus(int defBonus) {
        this.defBonus = defBonus;
    }

    public boolean isThunderPotion() {
        return thunderPotion;
    }

    public void setThunderPotion(boolean thunderPotion) {
        this.thunderPotion = thunderPotion;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public List<AttackItem> getInventory() {
        return inventory;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}


