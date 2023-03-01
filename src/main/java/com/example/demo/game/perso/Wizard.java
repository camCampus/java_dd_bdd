package com.example.demo.game.perso;


import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Wizard extends Perso {

    public Wizard(String name, TypeCharacter type) {
        super(name, type);
    }

    @Override
    public String toString() {
        return  colorize(".~~~| "+getName()+" |~~~.", Attribute.TEXT_COLOR(255,255,0))+"\n"+
                "Life: " + getLife() +" \n"+
                "Power: " + getPower() +" \n"+
                "DefBonus: " + getDefBonus() +" \n"+
                "AttackBonus: " + getAttackBonus() +" \n";
    }


}
