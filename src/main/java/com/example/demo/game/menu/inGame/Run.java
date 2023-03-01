package com.example.demo.game.menu.inGame;

import com.example.demo.game.menu.Menu;
import com.example.demo.game.menu.MenuActionEntry;

public class Run implements MenuActionEntry {


    private boolean escape = false;
    private boolean canEscape;
    public Run(boolean canEscape) {
        this.canEscape = canEscape;
    }
    @Override
    public void apply(Menu menu) {
        this.escape = true;
    }
    @Override
    public String getLabel() {
        return "Run";
    }
    @Override
    public boolean isVisible() {
        return canEscape;
    }
    public boolean isEscape() {
        return this.escape;
    }
}
