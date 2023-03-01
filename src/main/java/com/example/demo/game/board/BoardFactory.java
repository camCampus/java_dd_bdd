package com.example.demo.game.board;

import com.example.demo.game.board.cell.*;

import java.util.List;
import java.util.Random;

public class BoardFactory {

    private Board board;
    private Random random;

    public BoardFactory(Board gameBoard) {
        this.random = new Random();
        fillBoard(gameBoard);
    }

    private void fillBoard(Board gameBoard) {
        List<Cell> board = gameBoard.getEntry();
        board = esayFill(board);
        board = hardFill(board);
        gameBoard.setEntry(board);
    }

    private List<Cell> esayFill(List<Cell> fillList) {
        int easyLootCell = 12;
        int esayMonsterCell = 12;
        int easyCount = esayMonsterCell + easyLootCell;

        while (easyCount > 0) {
            for (int i = 0; i < fillList.size() / 2; i++) {
                if (i - 1 >= 0) {
                    if (fillList.get(i - 1) instanceof LootCell) {
                        fillList.set(i, new MonsterCell(LevelSelection.Easy));

                    } else if (fillList.get(i - 1) instanceof MonsterCell) {
                        int rand = random.nextInt(2);
                        switch (rand) {
                            case 0 -> fillList.set(i, new LootCell(LevelSelection.Easy));
                            case 1 -> fillList.set(i, new EmptyCell());
                        }
                    } else if (fillList.get(i - 1) instanceof EmptyCell) {
                        int rand = random.nextInt(2);
                        switch (rand) {
                            case 0 -> fillList.set(i, new LootCell(LevelSelection.Easy));
                            //case 1 -> fillList.set(i, new MonsterCell(LevelSelection.Easy));
                        }
                    }
                }
                easyCount--;
            }
        }
        return fillList;
    }

    private List<Cell> hardFill(List<Cell> fillList) {
        int hardLootCell = 15;
        int hardMonsterCell = 15;
        int hardCount = hardLootCell + hardMonsterCell;

        while (hardCount > 0) {
            for (int i = 32; i < fillList.size(); i++) {
                if (i - 1 >= 0) {
                    if (fillList.get(i - 1) instanceof LootCell) {

                        fillList.set(i, new MonsterCell(LevelSelection.Hard));

                    } else if (fillList.get(i - 1) instanceof MonsterCell) {
                        int rand = random.nextInt(2);
                        switch (rand) {
                            case 0 -> fillList.set(i, new LootCell(LevelSelection.Hard));
                            case 1 -> fillList.set(i, new EmptyCell());
                        }
                    } else if (fillList.get(i - 1) instanceof EmptyCell) {
                        int rand = random.nextInt(2);
                        switch (rand) {
                            case 0 -> fillList.set(i, new LootCell(LevelSelection.Hard));
                            //case 1 -> fillList.set(i, new MonsterCell(LevelSelection.Hard));
                        }
                    }
                }
                hardCount--;
            }
        }
        return fillList;
    }


}