package com.example.demo.game.items;

import com.example.demo.game.monster.Monster;

public interface Item {

    void applyEffect();

    int applySpeBonus(Monster monster);


}
