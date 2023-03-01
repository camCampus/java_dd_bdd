package com.example.demo.game;

import com.example.demo.game.board.cell.Cell;
import com.example.demo.game.board.cell.LootCell;
import com.example.demo.game.board.cell.MonsterCell;
import com.example.demo.game.items.Item;
import com.example.demo.game.items.attack.AttackItem;
import com.example.demo.game.items.defense.DefenseItem;
import com.example.demo.game.items.heal.PotionItem;
import com.example.demo.game.items.heal.ThunderPotion;
import com.example.demo.game.monster.Monster;
import com.example.demo.game.perso.Perso;

import java.sql.*;
import java.util.List;

public class Database {

    private Connection connection;
    private final Perso player;

    private boolean update;

    public Database() throws ClassNotFoundException, SQLException {
        this.update = false;
        this.player = App.getInstance().getPersonnage();
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Dragon", "root", "123Campus");
    }


    public void savePlayer(int boardId) throws SQLException {
        if (this.player != null) {

            //-----| Requete pour créer l'inventaire du joueur et récuperer l'id de l'inventaire

            String inventoryQuery = "INSERT INTO `inventory`" + "VALUES(?)";
            PreparedStatement inventoryStatement = connection.prepareStatement(inventoryQuery, Statement.RETURN_GENERATED_KEYS);
            inventoryStatement.setNull(1, Types.INTEGER);
            inventoryStatement.executeUpdate();
            ResultSet keys = inventoryStatement.getGeneratedKeys();
            keys.next();


            //-----| Requete pour sauvegarder le joueur

            String playerQuery = "INSERT INTO `player`" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement playerStatement = connection.prepareStatement(playerQuery);
            playerStatement.setNull(1, Types.INTEGER);
            playerStatement.setString(2, this.player.getName());
            playerStatement.setString(3, this.player.getType().toString());
            playerStatement.setInt(4, this.player.getLife());
            playerStatement.setInt(5, this.player.getPower());
            playerStatement.setBoolean(6, this.player.isThunderPotion());
            playerStatement.setInt(7, this.player.getPosition());
            playerStatement.setInt(8, boardId);
            playerStatement.setInt(9, keys.getInt(1));
            playerStatement.executeUpdate();
        }

    }

    public void checkIfPlayerAlreadyExist() throws SQLException {

        String nameQuery = "SELECT * FROM `player` WHERE name= '" + this.player.getName() + "'";

        PreparedStatement nameStatement = connection.prepareStatement(nameQuery);
        nameStatement.executeQuery();
        ResultSet resName = nameStatement.getResultSet();
        if (resName != null) {
            System.out.println("Name already use");
            while (resName.next()) {
                System.out.println("Id: " + resName.getInt("idplayer") + "\n" +
                        "Name: " + resName.getString("name") + "\n" +
                        "Type: " + resName.getString("type") + "\n" +
                        "Life: " + resName.getInt("life") + "\n" +
                        "Power: " + resName.getInt("power") + "\n" +
                        "Thunderpotion: " + resName.getString("thunderpotion"));
            }
        }
    }

    public void saveGame() throws SQLException {
        List<Cell> gameBoard = App.getInstance().getGame().getGameBoard();

        // Sauvegarde le board et récupère son id.
        String boardQuery = "INSERT INTO `board`" + "VALUES(?)";
        PreparedStatement boardStatement = connection.prepareStatement(boardQuery, Statement.RETURN_GENERATED_KEYS);
        boardStatement.setNull(1, Types.INTEGER);
        boardStatement.executeUpdate();
        ResultSet keys = boardStatement.getGeneratedKeys();
        keys.next();

        int boardId = keys.getInt(1);

        //Sauvegarde du joueur
        savePlayer(boardId);

        for (int i = 0; i < gameBoard.size(); i++) {
            if (gameBoard.get(i) instanceof MonsterCell) {
                Monster monster = ((MonsterCell) gameBoard.get(i)).getMonster();
                int monsterId = saveMonster(monster);
                saveMonsterCell(i, boardId, monsterId);
            } else if (gameBoard.get(i) instanceof LootCell) {
                int itemId = saveItem((LootCell) gameBoard.get(i));
                saveLootCell(i, boardId, itemId);
            } else {
                saveEmptyCell(i, boardId);
            }
        }
    }

    private void saveMonsterCell(int position, int boardId, int monsterId) throws SQLException {
        String monsterCellQuery = "INSERT INTO `cell_monster`" +
                "(`cell_monster_id`,  `position`, `board_id`, `monster_id`)" +
                "VALUES(?, ?, ?, ?)";
        PreparedStatement monsterCellStatement = connection.prepareStatement(monsterCellQuery);
        monsterCellStatement.setNull(1, Types.INTEGER);
        monsterCellStatement.setInt(2, position);
        monsterCellStatement.setInt(3, boardId);
        monsterCellStatement.setInt(4, monsterId);
        monsterCellStatement.executeUpdate();
    }

    private int saveMonster(Monster monster) throws SQLException {
        String monsterQuery = "INSERT INTO `monster`" +
                "(`monster_id`,  `life`, `power`, `type`)" +
                "VALUES(?, ?, ?, ?)";
        PreparedStatement monsterStatement = connection.prepareStatement(monsterQuery, Statement.RETURN_GENERATED_KEYS);
        monsterStatement.setNull(1, Types.INTEGER);
        monsterStatement.setInt(2, monster.getLife());
        monsterStatement.setInt(3, monster.getPower());
        monsterStatement.setString(4, monster.getClass().getSimpleName());
        monsterStatement.executeUpdate();
        ResultSet key = monsterStatement.getGeneratedKeys();
        key.next();

        return key.getInt(1);
    }

    private void saveLootCell(int position, int boardId, int itemId) throws SQLException {
        String lootCellQuery = "INSERT INTO `cell_loot`" +
                "(`cell_loot_id`,  `position`, `board_id`, `item_id`)" +
                "VALUES(?, ?, ?, ?)";
        PreparedStatement monsterCellStatement = connection.prepareStatement(lootCellQuery);
        monsterCellStatement.setNull(1, Types.INTEGER);
        monsterCellStatement.setInt(2, position);
        monsterCellStatement.setInt(3, boardId);
        monsterCellStatement.setInt(4, itemId);
        monsterCellStatement.executeUpdate();
    }

    private int saveItem(LootCell lootCell) throws SQLException {
        Item item = lootCell.getAllItem();
        int k = 0;


        if (item instanceof AttackItem) {
            String itemQuery = "INSERT INTO `items`" +
                    "(`item_id`,  `name`, `stats`, `description`, `spebonus`)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement itemStatement = connection.prepareStatement(itemQuery, Statement.RETURN_GENERATED_KEYS);
            itemStatement.setNull(1, Types.INTEGER);
            itemStatement.setString(2, ((AttackItem) item).getName());
            ;
            itemStatement.setInt(3, ((AttackItem) item).getStats());
            itemStatement.setString(4, ((AttackItem) item).getDescription());
            itemStatement.setBoolean(5, ((AttackItem) item).isSpeBonus());
            itemStatement.executeUpdate();
            ResultSet key = itemStatement.getGeneratedKeys();
            key.next();
            k = key.getInt(1);

        } else if (item instanceof DefenseItem) {
            String itemQuery = "INSERT INTO `items`" +
                    "(`item_id`,  `name`, `stats`, `description`, `spebonus`)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement itemStatement = connection.prepareStatement(itemQuery, Statement.RETURN_GENERATED_KEYS);
            itemStatement.setNull(1, Types.INTEGER);
            itemStatement.setString(2, ((DefenseItem) item).getName());
            ;
            itemStatement.setInt(3, ((DefenseItem) item).getStats());
            itemStatement.setString(4, ((DefenseItem) item).getDescription());
            itemStatement.setBoolean(5, false);
            itemStatement.executeUpdate();
            ResultSet key = itemStatement.getGeneratedKeys();
            key.next();
            k = key.getInt(1);

        } else if (item instanceof PotionItem) {
            String itemQuery = "INSERT INTO `items`" +
                    "(`item_id`,  `name`, `stats`, `description`, `spebonus`)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement itemStatement = connection.prepareStatement(itemQuery, Statement.RETURN_GENERATED_KEYS);
            itemStatement.setNull(1, Types.INTEGER);
            itemStatement.setString(2, "heal");
            ;
            itemStatement.setInt(3, ((PotionItem) item).getUp());
            itemStatement.setString(4, "life potion");
            itemStatement.setBoolean(5, false);
            itemStatement.executeUpdate();
            ResultSet key = itemStatement.getGeneratedKeys();
            key.next();
            k = key.getInt(1);
        } else {
            String itemQuery = "INSERT INTO `items`" +
                    "(`item_id`,  `name`, `stats`, `description`, `spebonus`)" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement itemStatement = connection.prepareStatement(itemQuery, Statement.RETURN_GENERATED_KEYS);
            itemStatement.setNull(1, Types.INTEGER);
            itemStatement.setString(2, "bonus");
            ;
            itemStatement.setInt(3, 0);
            itemStatement.setString(4, "thunder potion");
            itemStatement.setBoolean(5, false);
            itemStatement.executeUpdate();
            ResultSet key = itemStatement.getGeneratedKeys();
            key.next();
            k = key.getInt(1);
        }
        return k;
    }

    private void saveEmptyCell(int position, int boardId) throws SQLException {
        String emptyQuery = null;
        if (this.update) {
            emptyQuery = "UPDATE `cell_empty`" + "SET (?,?,?)";
        } else {
            emptyQuery = "INSERT INTO `cell_empty`" + "VALUES(?,?,?)";

        }

        PreparedStatement emptyCellStatement = connection.prepareStatement(emptyQuery);
        emptyCellStatement.setNull(1, Types.INTEGER);
        emptyCellStatement.setInt(2, position);
        emptyCellStatement.setInt(3, boardId);
        emptyCellStatement.executeUpdate();
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
