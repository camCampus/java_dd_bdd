package com.example.demo.game.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private Panel panel;

    public KeyboardInput(Panel panel) {
        this.panel = panel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        switch (e.getKeyCode()) {
            case 39 -> panel.changeXDelta(5);
            case 37 -> panel.changeXDelta(-5);
        }
        }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
