package control;

import main.Handler;
import objects.GameObject;
import utility.ID;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Miki on 17.03.2017.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //Player one keybinds
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5);
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(1);}
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //Player one keybinds
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
