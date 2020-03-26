package crawling.entertainment.feed_the_sanke.entity.snake;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.entity.Entity;
import crawling.entertainment.feed_the_sanke.entity.GameMoveAction;
import crawling.entertainment.feed_the_sanke.gfx.Asset;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeBody extends Entity implements GameMoveAction, Followable {

    private Followable followable;

    public SnakeBody(Handler handler, float x, float y, int width, int height, Followable followable) {
        super(handler, x, y, width, height);
        this.followable = followable;
        setCollisionBox();
    }

    @Override
    public void move() {
        if (getEntityXPosition() + getEntityWidth() < followable.getPosition().x) {
            setEntityYPosition(followable.getPosition().y);
            setEntityXPosition(followable.getPosition().x - getEntityWidth());
            setCollisionBox();
        }
        if (getEntityXPosition() -getEntityWidth() > followable.getPosition().x) {
            setEntityYPosition(followable.getPosition().y);
            setEntityXPosition(followable.getPosition().x + getEntityWidth());
            setCollisionBox();
        }
        if (getEntityYPosition() + getEntityHeight() < followable.getPosition().y) {
            setEntityXPosition(followable.getPosition().x);
            setEntityYPosition(followable.getPosition().y - getEntityHeight());
            setCollisionBox();
        }
        if (getEntityYPosition() - getEntityHeight() > followable.getPosition().y) {
            setEntityXPosition(followable.getPosition().x);
            setEntityYPosition(followable.getPosition().y + getEntityHeight());
            setCollisionBox();
        }
    }

    @Override
    public void hurt() {

    }

    @Override
    public void gamePlay() {
        move();
    }

    @Override
    public void render(Graphics graphic) {
        graphic.drawImage(Asset.snakeBody, (int) getEntityXPosition(), (int) getEntityYPosition(), getEntityWidth(), getEntityHeight(), null);
    }

    @Override
    public void endGameForEntity() {
        setActive(false);
    }

    @Override
    public Point getPosition() {
        return new Point((int) getEntityXPosition(), (int) getEntityYPosition());
    }
}
