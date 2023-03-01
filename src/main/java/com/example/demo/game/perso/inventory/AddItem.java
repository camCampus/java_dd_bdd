package com.example.demo.game.perso.inventory;

import com.example.demo.game.App;
import com.example.demo.game.items.attack.AttackItem;
import com.example.demo.game.menu.Menu;
import com.example.demo.game.menu.MenuActionEntry;
import com.example.demo.game.perso.Perso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddItem implements MenuActionEntry {

    private Scanner scanner;
    private Perso player;
    private AttackItem item;
    public AddItem(Scanner scanner, AttackItem item) {
        this.scanner = scanner;
        this.player = App.getInstance().getPersonnage();
        this.item = item;
    }

    @Override
    public void apply(Menu menu) {


            List<MenuActionEntry> drop = new ArrayList<>();
            drop.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    player.getInventory().set(0, item);
                }

                @Override
                public String getLabel() {
                    return player.getInventory().get(0).getName();
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });

            drop.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    player.getInventory().set(1, item);;
                }

                @Override
                public String getLabel() {
                    return player.getInventory().get(1).getName();
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });
            new Menu(this.scanner, drop).runMenu();
    }

    @Override
    public String getLabel() {
        return "Remove from inventory";
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
