package crawling.entertainment.feed_the_sanke;

import crawling.entertainment.feed_the_sanke.display.Display;
import crawling.entertainment.feed_the_sanke.input.KeyManager;
import crawling.entertainment.feed_the_sanke.world.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public int getGameWidth() {
        return game.getWidth();
    }

    public int getGameHeight() {
        return game.getHeight();
    }

    public String getUserName() {
        return  game.getUsername();
    }

    public void endGame() {
        game.endGame();
    }
}
