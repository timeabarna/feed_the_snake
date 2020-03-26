package crawling.entertainment.feed_the_sanke.state;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.world.World;
import java.awt.Graphics;

public class GameState extends State {

    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler);
        handler.setWorld(world);
    }

    @Override
    public void gamePlay() {
        world.gamePlay();
    }

    @Override
    public void render(Graphics graphic) {
        world.render(graphic);
    }
}
