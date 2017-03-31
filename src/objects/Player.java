package objects;

import main.Handler;
import utility.ID;

import java.awt.*;

import static main.Game.HEIGHT;
import static main.Game.WIDTH;
import static main.Game.clamp;

/**
 * Created by Miki on 17.03.2017.
 */
public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                HUD.Health-=2;
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = clamp(x, WIDTH - 38, 0);
        y = clamp(y, HEIGHT - 60, 0);

        collision();
    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.GREEN);
        g2d.draw(getBounds());

        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);

    }

}
