package com.example.demo.game.window;

import javax.swing.*;
import java.awt.Panel;
import java.awt.*;

public class GameWindow {


    private final ScrollPane scrollPanel;
    private JFrame jframe;


    public GameWindow(com.example.demo.game.window.Panel panel) {

        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setSize(6*64,360);

        this.scrollPanel = new ScrollPane(2);
        this.scrollPanel.add(panel);
        jframe.add(this.scrollPanel);

    }



    public void goTo(int x, int y) {
        this.scrollPanel.setScrollPosition(x,y);
    }


}
