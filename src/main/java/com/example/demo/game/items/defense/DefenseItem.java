package com.example.demo.game.items.defense;


import com.diogonunes.jcolor.Attribute;
import com.example.demo.game.App;
import com.example.demo.game.items.Item;
import com.example.demo.game.perso.Perso;
import com.example.demo.game.perso.TypeCharacter;

import static com.diogonunes.jcolor.Ansi.colorize;

public abstract class DefenseItem implements Item {


    private String name;
    private int stats;
    private String description;

    protected abstract TypeCharacter getUseBy();

    @Override
    public void applyEffect() {
        Perso character = App.getInstance().getPersonnage();
        TypeCharacter type = character.getType();
        System.out.println(colorize(".~~~| ITEM |~~~.", Attribute.TEXT_COLOR(255,0,255)));
        if (type == getUseBy()) {

            if (character.getDefenseItem() == null) {
                character.setDefenseItem(this);
                character.setDefBonus(this.stats);
                System.out.println("You found and equip this item: " + "\n" + this);

            } else if (character.getDefenseItem().getStats() < this.getStats()) {
                character.setDefenseItem(this);
                character.setDefBonus(this.stats);
                System.out.println("You found and equip a better item nice !! : " + "\n" + this);
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

    @Override
    public String toString() {
        System.out.println(colorize(".~~~| ITEM |~~~.", Attribute.TEXT_COLOR(255,0,255)));
        return getName() + "\n" + "defense bonus + " + getStats() + "\n" + "description : " + "\n" + getDescription();
    }
}
