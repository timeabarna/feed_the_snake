package crawling.entertainment.feed_the_sanke.entity;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.entity.food.Food;
import crawling.entertainment.feed_the_sanke.entity.snake.SnakeBody;
import crawling.entertainment.feed_the_sanke.entity.snake.SnakeHead;
import crawling.entertainment.feed_the_sanke.tile.Tile;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Entity {

    private Handler handler;
    private float entityXPosition;
    private float entityYPosition;
    private int entityWidth;
    private int entityHeight;
    private Rectangle collisionBound;
    private boolean active = true;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.entityXPosition = x;
        this.entityYPosition = y;
        this.entityWidth = width;
        this.entityHeight = height;
        collisionBound = new Rectangle(0, 0, width, height);
    }

    public abstract void gamePlay();
    public abstract void render(Graphics graphic);
    public abstract void endGameForEntity();

    public void setCollisionBox() {
        collisionBound = (new Rectangle((int) getEntityXPosition(), (int) getEntityYPosition(), getEntityWidth(), getEntityHeight()));
    }

    public boolean checkEntityCollision(float xOffset, float yOffset) {
        for (Entity entity: handler.getWorld().getEntityManager().getEntities()) {
            if (this != entity && entity.getCollisionBound().intersects(getCollisionBound())) {
                if (this instanceof Food) {
                    return true;
                }
                if (entity instanceof Food) {
                    if (entity.isActive() && this instanceof SnakeHead) {
                        ((SnakeHead) this).eat();
                        entity.setActive(false);
                    }
                    return true;
                } else if (entity instanceof SnakeBody) {
                    if (this instanceof SnakeHead) {
                        ((SnakeHead) this).hurt();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public Point randomizeCoordinates() {
        return new Point(ThreadLocalRandom.current().nextInt(Tile.TILE_WIDTH + 2, getHandler().getGameWidth() - getEntityWidth() - Tile.TILE_WIDTH- 2),
                ThreadLocalRandom.current().nextInt(Tile.TILE_HEIGHT + 2, getHandler().getGameHeight() - getEntityHeight() - Tile.TILE_HEIGHT- 2));
    }

    public float getEntityXPosition() {
        return entityXPosition;
    }

    public void setEntityXPosition(float entityXPosition) {
        this.entityXPosition = entityXPosition;
    }

    public float getEntityYPosition() {
        return entityYPosition;
    }

    public void setEntityYPosition(float entityYPosition) {
        this.entityYPosition = entityYPosition;
    }

    public int getEntityWidth() {
        return entityWidth;
    }

    public int getEntityHeight() {
        return entityHeight;
    }

    public Rectangle getCollisionBound() {
        return collisionBound;
    }

    public Handler getHandler() {
        return handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
