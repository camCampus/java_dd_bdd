package com.example.demo.game.menu;

import com.example.demo.game.App;
import com.example.demo.game.asset.AsciiArt;
import com.example.demo.game.exception.PersoOutOfMapException;
import com.example.demo.game.game.Game;

import java.util.Scanner;

import static com.example.demo.game.utils.Utils.waitSecond;

public class LunchGame implements MenuActionEntry {

    private String label;
    private Scanner scanner;
    private boolean visible;

    private AsciiArt asciiArt;
    private int longWait;
    private int shortWait;

    public LunchGame(Scanner scanner, AsciiArt asciiArt) {
        this.label = "Lunch Game";
        this.scanner = scanner;
        this.visible = false;
        this.asciiArt = asciiArt;
        this.longWait = 20;
        this.shortWait = 10;
    }

    @Override
    public void apply(Menu menu) {
        System.out.println("Create Game");
        asciiArt.journeyStart();
        waitSecond(longWait);

        System.out.println("After a long trip, you see the famous castle of Mogot and is dungeon");
        waitSecond(longWait);

        asciiArt.drawCastle();
        waitSecond(longWait);

        System.out.println("You are enter in the dungeon of Morgot");
        waitSecond(longWait);

        asciiArt.drawDoor();
        waitSecond(longWait);

        Game game = App.getInstance().getGame();

        try {
            game.gameLoop();
        } catch (PersoOutOfMapException e) {
            System.out.println(e.getMessage());
            asciiArt.outOfMap();
            System.out.println("Dragon kill you");
        }
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean isVisible() {
        if (App.getInstance().getPersonnage() != null) {
            this.visible = true;
        }
        return this.visible;
    }
}
