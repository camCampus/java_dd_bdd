package com.example.demo.game.menu.createPlayer;

import com.example.demo.game.App;
import com.example.demo.game.menu.Menu;
import com.example.demo.game.menu.MenuActionEntry;
import com.example.demo.game.perso.Perso;
import com.example.demo.game.perso.TypeCharacter;
import com.example.demo.game.perso.Warrior;
import com.example.demo.game.perso.Wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreatePlayerMenu implements MenuActionEntry {

    private String label;
    private Scanner scanner;

    private Random random;
    private boolean visible;
    private String playerName;
    public CreatePlayerMenu (Scanner scanner, Random random) {
        this.label = "Create Player";
        this.scanner = scanner;
        this.random = random;
        this.visible = true;
        this.playerName = "Unnamed";
    }

    @Override
    public void apply(Menu menu) {

        List<MenuActionEntry> entries = new ArrayList<>();
        System.out.println("Enter the player name");
        CreatePlayerMenu.this.playerName = CreatePlayerMenu.this.scanner.nextLine();
        entries.add(new MenuActionEntry() {
            @Override
            public void apply(Menu menu) {
                Perso character = new Warrior(playerName, TypeCharacter.Warrior);
                App.getInstance().setPersonnage(character, CreatePlayerMenu.this.random, CreatePlayerMenu.this.scanner);
                System.out.println(App.getInstance().getPersonnage().toString());
            }

            @Override
            public String getLabel() {
                return TypeCharacter.Warrior.name();
            }

            @Override
            public boolean isVisible() {
                return true;
            }
        });

        entries.add(new MenuActionEntry() {
            @Override
            public void apply(Menu menu) {
                Perso character = new Wizard(playerName, TypeCharacter.Wizard);
                App.getInstance().setPersonnage(character, CreatePlayerMenu.this.random, CreatePlayerMenu.this.scanner);
                System.out.println(App.getInstance().getPersonnage().toString());
            }

            @Override
            public String getLabel() {
                return TypeCharacter.Wizard.name();
            }

            @Override
            public boolean isVisible() {
                return true;
            }
        });

        Menu subMenu = new Menu(this.scanner, entries);
        subMenu.runMenu();
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
