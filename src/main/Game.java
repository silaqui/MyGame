package main;

import control.KeyInput;
import objects.BasicEnemy;
import objects.HUD;
import objects.Player;
import utility.ID;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 6691247796639148462L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private HUD hud;
    private Handler handler;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        this.addKeyListener(new KeyInput(handler));

        new window.Window(WIDTH, HEIGHT, "Yeah game!", this);

        handler.addObject(new Player(WIDTH / 2, HEIGHT / 2, ID.Player, handler));
//        for (int i = 0; i < 20; i++)

        Random random = new Random(10);
        handler.addObject(new BasicEnemy(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.BasicEnemy));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static int clamp(int var, int max, int min) {
        if (var >= max) {
            var = max;
        }
        if (var <= min) {
            var = min;
        }
        return var;
    }

    public static void main(String[] args) {

        new Game();

    }

}
