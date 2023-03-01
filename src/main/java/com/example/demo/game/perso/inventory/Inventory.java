package com.example.demo.game.perso.inventory;

import com.diogonunes.jcolor.Attribute;
import com.example.demo.game.App;
import com.example.demo.game.items.Item;
import com.example.demo.game.menu.Menu;
import com.example.demo.game.menu.MenuActionEntry;
import com.example.demo.game.perso.Perso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Inventory implements MenuActionEntry {
    private List<Item> items;
    private Perso player;

    private Scanner scanner;
    private boolean runInventory;
    public Inventory(Scanner scanner) {
        this.scanner = scanner;
        this.items = new ArrayList<>();
        this.runInventory = true;
        this.player = App.getInstance().getPersonnage();
    }


    @Override
    public void apply(Menu menu) {
        System.out.println(colorize(".~~~| INVENTORY |~~~.", Attribute.TEXT_COLOR(255, 0, 255)));
        System.out.println("Select item to equip");

        List<MenuActionEntry> items = new ArrayList<>();

        for (int i =0; i<player.getInventory().size(); i++){
            int finalI = i;
            items.add(new MenuActionEntry() {
                @Override
                public void apply(Menu menu) {
                    System.out.println("equip item...");
                    switchItem(finalI);
                }

                @Override
                public String getLabel() {
                    return player.getInventory().get(finalI).getName() + "\n" + "Power: "+ player.getInventory().get(finalI).getStats();
                }

                @Override
                public boolean isVisible() {
                    return true;
                }
            });
        }
        new Menu(this.scanner, items).runMenu();
    }

    private void switchItem(int item) {
        player.setAttackItem(player.getInventory().get(item));
    }
    @Override
    public String getLabel() {
        return "Inventory";
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
