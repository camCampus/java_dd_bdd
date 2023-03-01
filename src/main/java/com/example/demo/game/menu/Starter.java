package com.example.demo.game.menu;

import com.example.demo.game.App;
import com.example.demo.game.board.LevelSelection;
import com.example.demo.game.items.attack.Bow;
import com.example.demo.game.items.attack.Spell;
import com.example.demo.game.items.attack.Stick;
import com.example.demo.game.items.attack.Sword;
import com.example.demo.game.perso.Perso;
import com.example.demo.game.perso.TypeCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Starter implements MenuActionEntry{

    private Scanner scanner;
    private Perso player;
    public Starter(Scanner scanner) {
        this.scanner = scanner;
        this.player = App.getInstance().getPersonnage();
    }

    @Override
    public void apply(Menu menu) {
        List<MenuActionEntry> selection = new ArrayList<>();

        if (player.getType() == TypeCharacter.Warrior) {
            selection.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    player.getInventory().add(new Sword(LevelSelection.Easy));
                    player.setAttackItem(player.getInventory().get(0));
                    System.out.println(player.getInventory().get(0).toString());
                }

                @Override
                public String getLabel() {

                    return "Sword";
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });

            selection.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    player.getInventory().add(new Bow(LevelSelection.Easy));
                    player.setAttackItem(player.getInventory().get(0));
                    System.out.println(player.getInventory().get(0).toString());
                }

                @Override
                public String getLabel() {
                    return "Bow";
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });
        } else {
            selection.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    player.getInventory().add(new Stick(LevelSelection.Easy));
                    player.setAttackItem(player.getInventory().get(0));
                    System.out.println(player.getInventory().get(0).toString());
                }

                @Override
                public String getLabel() {

                    return "Stick";
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });

            selection.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    player.getInventory().add(new Spell(LevelSelection.Easy));
                    player.setAttackItem(player.getInventory().get(0));
                    System.out.println(player.getInventory().get(0).toString());
                }

                @Override
                public String getLabel() {
                    return "Spell";
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });
        }


        new Menu(this.scanner, selection).runMenu();
    }

    @Override
    public String getLabel() {
        return "Select weapon";
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
