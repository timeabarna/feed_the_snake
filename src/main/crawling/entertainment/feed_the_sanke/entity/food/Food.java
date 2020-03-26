package crawling.entertainment.feed_the_sanke.entity.food;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.entity.Entity;
import crawling.entertainment.feed_the_sanke.gfx.Asset;
import java.awt.Graphics;
import java.awt.Point;

public class Food extends Entity {

    public static final int DEFAULT_FOOD_WIDTH = 32;
    public static final int DEFAULT_FOOD_HEIGHT = 32;

    public Food(Handler handler) {
        super(handler, 0, 0, DEFAULT_FOOD_WIDTH, DEFAULT_FOOD_HEIGHT);
        setActive(false);
    }

    @Override
    public void gamePlay() {
        if (!isActive()) {
            Point coordinate;
            getHandler().getWorld().getEntityManager().addEntity(this);
            do  {
                coordinate = randomizeCoordinates();
                setEntityXPosition(coordinate.x);
                setEntityYPosition(coordinate.y);
                setCollisionBox();
            } while (checkEntityCollision(coordinate.x, coordinate.y));
            setActive(true);
        }
    }

    @Override
    public void render(Graphics graphic) {
        graphic.drawImage(Asset.food, (int) getEntityXPosition(), (int) getEntityYPosition(), getEntityWidth(), getEntityHeight(), null);
    }

    @Override
    public void endGameForEntity() {
        setActive(false);
    }
}
