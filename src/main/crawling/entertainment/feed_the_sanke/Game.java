package crawling.entertainment.feed_the_sanke;

import crawling.entertainment.feed_the_sanke.display.Display;
import crawling.entertainment.feed_the_sanke.gfx.Asset;
import crawling.entertainment.feed_the_sanke.input.KeyManager;
import crawling.entertainment.feed_the_sanke.state.GameState;
import crawling.entertainment.feed_the_sanke.state.EndGameState;
import crawling.entertainment.feed_the_sanke.state.State;
import crawling.entertainment.feed_the_sanke.state.StateManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable {

    private static final long ONE_SECOND_IN_NANOSECONDS = TimeUnit.SECONDS.toNanos(1);

    private Display display;
    private int width;
    private int height;
    private String title;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private EndGameState endGameState;
    private GameState gameState;
    private KeyManager keyManager;
    private Handler handler;
    private String username;

    private boolean running = false;
    private Thread thread;

    public Game(String title, int width, int height, String username) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.username = username;
        keyManager = new KeyManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Asset.init();

        handler = new Handler(this);
        endGameState = new EndGameState(handler);
        gameState = new GameState(handler);
        StateManager.setCurrentState(gameState);

    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = ONE_SECOND_IN_NANOSECONDS / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while(running) {
            now = System.nanoTime();
            delta = delta + (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1) {
                gamePlay();
                render();
                delta = delta - 1;
            }
        }

        stop();
    }

    private void gamePlay() {
        keyManager.gamePlay();
        State currentState = StateManager.getCurrentState();
        if (currentState != null) {
            currentState.gamePlay();
        }
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);

        State currentState = StateManager.getCurrentState();
        if (currentState != null) {
            currentState.render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    public synchronized  void start() {

        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {

        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endGame() {
        StateManager.setCurrentState(endGameState);
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Display getDisplay() {
        return display;
    }

    public String getUsername() {
        return username;
    }
}
