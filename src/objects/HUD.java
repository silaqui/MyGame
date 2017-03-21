package objects;

import java.awt.*;

import static main.Game.clamp;

/**
 * Created by Miki on 21.03.2017.
 */
public class HUD {

    public static int Health = 100;

    public void tick() {
        Health--;
        Health=clamp(Health,200,0);
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15,15,200,15);
        g.setColor(Color.GREEN);
        g.fillRect(15,15,Health*2,15);
        g.setColor(Color.WHITE);
        g.drawRect(15,15,200,15);
    }
}
