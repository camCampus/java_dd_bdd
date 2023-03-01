package com.example.demo.game.board.cell;

import com.example.demo.game.board.Board;
import com.example.demo.game.items.Item;

import java.util.List;

public abstract class Cell {
    @Override
    public String toString() {
        return "Cell{}";
    }

    public void fillBoard(Board board) {
        List cell = board.getEntry();

    }
    public  abstract Cell getTypeCell();
    public abstract Item getAllItem();

    public abstract void apply();
}
