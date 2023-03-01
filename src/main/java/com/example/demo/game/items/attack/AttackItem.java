package com.example.demo.game.items.attack;

import com.diogonunes.jcolor.Attribute;
import com.example.demo.game.App;
import com.example.demo.game.asset.AsciiArt;
import com.example.demo.game.items.Item;
import com.example.demo.game.menu.Menu;
import com.example.demo.game.menu.MenuActionEntry;
import com.example.demo.game.perso.Perso;
import com.example.demo.game.perso.TypeCharacter;
import com.example.demo.game.perso.inventory.AddItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public abstract class AttackItem implements Item {

    private String name;
    private int stats;
    private String description;

    private boolean speBonus = false;
    private Scanner scanner = new Scanner(System.in);


    protected abstract TypeCharacter getUseBy();


    @Override
    public void applyEffect() {
        Perso player = App.getInstance().getPersonnage();
        TypeCharacter type = player.getType();
        int resetPlayerPower = player.getPower();
        AsciiArt asciiArt = new AsciiArt();
        System.out.println(colorize(".~~~| ITEM |~~~.", Attribute.TEXT_COLOR(255,0,255)));
        System.out.println(this);
        if (type == getUseBy()) {


            if (player.getInventory().size() < 2) {
                player.getInventory().add(this);

            } else {
                System.out.println(colorize(".~~~| INVENTORY |~~~.", Attribute.TEXT_COLOR(255,0,255)));
                System.out.println("Inventory" + player.getInventory().size() +"/2");
                System.out.println(player.getInventory().toString());
                List<MenuActionEntry> inventoryLoot = new ArrayList<>();
                inventoryLoot.add(new AddItem(this.scanner, this));
                inventoryLoot.add(new MenuActionEntry() {
                    @Override
                    public void apply(Menu menu) {
                    }

                    @Override
                    public String getLabel() {
                        return "Don't add new item";
                    }

                    @Override
                    public boolean isVisible() {
                        return true;
                    }
                });
                new Menu(this.scanner, inventoryLoot).runMenu();
            }

        } else {
            System.out.println("This item is not for you sorry");
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSpeBonus() {
        return speBonus;
    }

    public void setSpeBonus(boolean speBonus) {
        this.speBonus = speBonus;
    }
    @Override
    public String toString() {
        return getName() + "\n" + "attack bonus + " + getStats() + "\n" + "description : " + "\n" + getDescription();
    }
}
