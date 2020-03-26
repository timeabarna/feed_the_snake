package crawling.entertainment.feed_the_sanke.state;

import crawling.entertainment.feed_the_sanke.Handler;
import java.awt.Graphics;

public abstract class State {

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract  void gamePlay();
    public abstract  void render(Graphics graphic);
}
