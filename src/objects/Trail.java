package objects;

import main.Handler;
import utility.ID;

import java.awt.*;

/**
 * Created by Miki on 31.03.2017.
 */
public class Trail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    float life;

    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.handler=handler;
        this.life=life;

    }

    @Override
    public void tick() {
        if (alpha>life)
        {
            alpha-=life-0.001f;
        }else
        {
            handler.removeObject(this);
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g2d.setComposite(makeTransparent(1));

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_ATOP;
        return (AlphaComposite.getInstance(type, alpha));

    }
}
