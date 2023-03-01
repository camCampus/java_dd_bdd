package com.example.demo.game.board;

import com.example.demo.game.board.cell.Cell;
import com.example.demo.game.board.cell.EmptyCell;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BOARD_LENGTH = 64;
    private List<Cell> entry;
    public Board() {
        this.entry = fillBoard();
    }

    public List<Cell> fillBoard() {
        List<Cell> allCell = new ArrayList<>();
        for (int i = 0; i < BOARD_LENGTH; i++) {
            allCell.add(new EmptyCell());
            }
        return allCell;
        }

    @Override
    public String toString() {
        return "Board{" +
                "entry=" + entry +
                '}';
    }

    public List<Cell> getEntry() {
        return entry;
    }

    public void setEntry(List<Cell> entry) {
        this.entry = entry;
    }

    public int getBoardLengt() {
        return BOARD_LENGTH;
    }
}

