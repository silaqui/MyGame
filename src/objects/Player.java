package objects;

import main.Game;
import utility.ID;

import java.awt.*;
import java.util.Random;

import static main.Game.HEIGHT;
import static main.Game.WIDTH;
import static main.Game.clamp;

/**
 * Created by Miki on 17.03.2017.
 */
public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x=clamp(x, WIDTH-38, 0);
        y=clamp(y, HEIGHT-60, 0);
    }

    @Override
    public void render(Graphics g) {

        if (id == ID.Player) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, 32, 32);

    }

}
