package com.example.demo.game.board.cell;

import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.items.Item;
import com.example.demo.game.items.attack.*;
import com.example.demo.game.items.defense.DefenseItem;
import com.example.demo.game.items.defense.MagicShield;
import com.example.demo.game.items.defense.Shield;
import com.example.demo.game.items.heal.PotionItem;
import com.example.demo.game.items.heal.ThunderPotion;

import java.util.Random;

public class LootCell extends Cell {

 private Random random;
 private Item item;

 private LevelSelection level;
 public LootCell(LevelSelection level) {
     this.random = new Random();
     this.level = level;
     createLootItem();
 }
    private void createLootItem() {
     int rand = random.nextInt(3);

     switch (rand) {
         case 0 -> this.item = (Item) createAttackItem(this.level);
         case 1 -> this.item = (Item) createDefenseItem(this.level);
         case 2 -> this.item = createPotionItem();
     }
    }

    private AttackItem createAttackItem(LevelSelection level) {
     int rand = random.nextInt(4);
     AttackItem item = null;

        switch (rand) {
            case 0 -> item = new Sword(level);
            case 1 -> item = new Spell(level);
            case 2 -> item = new Bow(level);
            case 3 -> item = new Stick(level);
        }

        return item;
    }
    private DefenseItem createDefenseItem(LevelSelection level) {
        int rand = random.nextInt(2);
        DefenseItem item = null;

        switch (rand) {
            case 0 -> item = new Shield(level);
            case 1 -> item = new MagicShield(level);
        }

        return item;
    }
    private Item createPotionItem() {
        int rand = random.nextInt(2);
        Item item = null;

        switch (rand) {
            case 0 -> item = new PotionItem();
            case 1 -> item = new ThunderPotion();
        }
        return item;
    }


    @Override
    public Cell getTypeCell() {
        return this;
    }

    @Override
    public Item getAllItem() {
        return this.item;
    }

    @Override
    public void apply() {
     this.item.applyEffect();
    }
}
