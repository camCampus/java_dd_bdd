package com.example.demo.game.items.heal;

import com.diogonunes.jcolor.Attribute;
import com.example.demo.game.App;
import com.example.demo.game.items.Item;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.Perso;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ThunderPotion implements Item {



    @Override
    public void applyEffect() {
        Perso player = App.getInstance().getPersonnage();
        player.setThunderPotion(true);
        System.out.println(colorize(".~~~| ITEM |~~~.", Attribute.TEXT_COLOR(255,0,255)));
        System.out.println("ThunderPotion double your power for next fight");
    }

    @Override
    public int applySpeBonus(Monster monster) {
        return 0;
    }

}
