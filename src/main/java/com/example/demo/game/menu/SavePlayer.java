package com.example.demo.game.menu;

import com.example.demo.game.App;
import com.example.demo.game.Database;

import java.sql.SQLException;

public class SavePlayer implements MenuActionEntry{

    private boolean visible;


    public SavePlayer() {
        this.visible= false;
    }
    @Override
    public void apply(Menu menu) {
        //--------| DATABASE
        try {
           Database db = new Database();
           db.saveGame();
           db.setUpdate(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getLabel() {
        return "Save Your Player";
    }

    @Override
    public boolean isVisible() {
        if (App.getInstance().getPersonnage() != null){
            this.visible = true;
        }
        return this.visible;
    }
}
