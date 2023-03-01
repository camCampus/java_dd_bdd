package com.example.demo.game.menu.inGame;

import com.example.demo.game.board.cell.Cell;
import com.example.demo.game.board.cell.LootCell;
import com.example.demo.game.menu.Menu;
import com.example.demo.game.menu.MenuActionEntry;

public class Fight implements MenuActionEntry {

    private  Cell cell;

    public Fight (Cell cell){
        this.cell = cell;
    }
    @Override
    public void apply(Menu menu) {}
    @Override
    public String getLabel() {
        return "Fight";
    }
    @Override
    public boolean isVisible() {
        return true;
    }

    public boolean isFigth() {
        boolean v = true;
        if (cell instanceof LootCell) {
            v = false;
        }
        return false;
    }

}
