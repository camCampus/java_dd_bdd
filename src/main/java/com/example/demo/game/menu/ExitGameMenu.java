package com.example.demo.game.menu;

import java.util.Scanner;

public class ExitGameMenu implements MenuActionEntry{
    private String label;
    private Scanner scanner;
    private boolean visible;
    public ExitGameMenu (Scanner scanner) {
        this.label = "Exit Game";
        this.scanner = scanner;

    }
    @Override
    public void apply(Menu menu) {
        menu.exitGame();
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean isVisible() {
        return this.visible = true;
    }
}
