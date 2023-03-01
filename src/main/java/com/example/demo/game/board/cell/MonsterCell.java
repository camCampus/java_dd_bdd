package com.example.demo.game.board.cell;

import com.diogonunes.jcolor.Attribute;
import com.example.demo.game.App;
import com.example.demo.game.asset.AsciiArt;
import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.items.Item;
import com.example.demo.game.monster.*;
import com.example.demo.game.perso.Perso;
import com.example.demo.game.perso.Warrior;
import com.example.demo.game.perso.Wizard;

import java.util.Random;

import static com.diogonunes.jcolor.Ansi.colorize;

public class MonsterCell extends Cell {

    private Monster monster;
    private Random random;
    private AsciiArt asciiArt;

    public MonsterCell(LevelSelection level) {
        this.random = new Random();
        this.monster = createMonster(level);
        this.asciiArt = new AsciiArt();
    }

    private Monster createMonster(LevelSelection level) {
        int rand = random.nextInt(5);
        Monster monster = null;


        switch (rand) {
            case 0 -> monster = new Dragon(level);
            case 1 -> monster = new Gobelin(level);
            case 2 -> monster = new Sorcerer(level);
            case 3 -> monster = new Orcs(level);
            case 4 -> monster = new Demon(level);
        }
        return monster;
    }

    @Override
    public Cell getTypeCell() {
        return this;
    }

    @Override
    public Item getAllItem() {
        return null;
    }

    private boolean playerAttack(Perso player) {
        boolean alive = true;
        int speBonus = 0;


        if (player.getAttackItem() != null && player.getAttackItem().isSpeBonus()) {
            speBonus = player.getAttackItem().applySpeBonus(this.monster);
        }

        if (player.isThunderPotion()) {
            this.monster.setLife(this.monster.getLife() - ((damageCalc((player.getPower() + player.getAttackBonus())) + speBonus) * 2));
            player.setThunderPotion(false);
        } else {
            this.monster.setLife(this.monster.getLife() - ((damageCalc((player.getPower() + player.getAttackBonus())) + speBonus)));
        }

        System.out.println("You attack the monster and hit him with " + (player.getPower() + player.getAttackBonus()) + " + speBonus: " + speBonus);
        if (this.monster.getLife() <= 0) {
            System.out.println("You kill the monster");
            alive = false;
        } else {
            System.out.println("Monster life = " + this.monster.getLife());
        }
        return alive;
    }

    private void monsterAttack(Perso player) {
        System.out.println(colorize("The monster attack you !!", Attribute.RED_TEXT(), Attribute.WHITE_BACK()));

        if (player.getDefenseItem() != null) {
            int damage = damageCalc(this.monster.getPower()) - player.getDefenseItem().getStats();
            damage = Math.max(damage, 0);
            player.setLife(player.getLife() - damage);
            System.out.println("Monster deal you " + damage);
        } else {
            player.setLife(player.getLife() - damageCalc(this.monster.getPower()));
        }

        if (player.getLife() <= 0) {
            this.asciiArt.death();
            player.setAlive(false);
        }
    }

    @Override
    public void apply() {
        Perso player = App.getInstance().getPersonnage();

        boolean monsterIsAlive;

        //Player attaque
        monsterIsAlive = playerAttack(player);

        //Monster attaque
        if (monsterIsAlive) {
            if (this.monster instanceof Orcs && player instanceof Wizard) {
                System.out.println("Orcs don't see you");
            } else if (this.monster instanceof Demon && player instanceof Warrior) {
                System.out.println("Demon don't see you");
            } else {
                monsterAttack(player);
            }
        }
    }

    public Monster getMonster() {
        return monster;
    }

    private ChanceAttack randomAttack() {
        ChanceAttack chance;
        int rand = (int)(Math.random()*(100-0+1)+0);
        if(isBetween(rand,0, 80)){
            chance = ChanceAttack.Touch;
            System.out.println("Attack touch");
        }   else if(isBetween(rand,81, 96)){
            chance = ChanceAttack.Miss;
            System.out.println("Attack Miss");
        } else {
            chance = ChanceAttack.Critic;
            System.out.println("Attack Critic");
        }
        return chance;
    }
    private int damageCalc(int damage) {
        int givenDamge = 0;
        switch (randomAttack()) {
            case Touch -> givenDamge = damage;
            case Miss -> {}
            case Critic -> givenDamge = damage*3;
        }

        return givenDamge;
    }

    private boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
