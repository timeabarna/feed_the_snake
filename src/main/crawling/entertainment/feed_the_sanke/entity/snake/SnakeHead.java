package crawling.entertainment.feed_the_sanke.entity.snake;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.entity.Entity;
import crawling.entertainment.feed_the_sanke.entity.GameMoveAction;
import crawling.entertainment.feed_the_sanke.gfx.Asset;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SnakeHead extends Entity implements GameMoveAction, Followable {

    private static final int DEFAULT_HEALTH = 5;
    private static final float DEFAULT_SPEED = 1.0f;
    private static final int DEFAULT_SNAKE_WIDTH = 64;
    private static final int DEFAULT_SNAKE_HEIGHT = 64;

    private float xMove;
    private float yMove;
    private int health;
    private float speed;
    private int snakeBodyListSize;
    private int highestScore;
    private List<SnakeBody> snakeBodyList = new ArrayList();

    public SnakeHead(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_SNAKE_WIDTH, DEFAULT_SNAKE_HEIGHT);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        Point coordinate = randomizeCoordinates();
        setEntityXPosition(coordinate.x);
        setEntityYPosition(coordinate.y);
        setCollisionBox();
    }

    @Override
    public void gamePlay() {
        getInput();
        move();
    }

    private void getInput() {
        if (snakeBodyListSize == 0 && getHandler().getKeyManager().up) {
            xMove = 0;
            yMove = -speed;
        } else if (getHandler().getKeyManager().up && yMove != speed) {
            xMove = 0;
            yMove = -speed;
        }
        if (snakeBodyListSize == 0 && getHandler().getKeyManager().down) {
            xMove = 0;
            yMove = speed;
        } else if (getHandler().getKeyManager().down && yMove != -speed) {
            xMove = 0;
            yMove = speed;
        }
        if (snakeBodyListSize == 0 && getHandler().getKeyManager().left) {
            yMove = 0;
            xMove = -speed;
        } else if (getHandler().getKeyManager().left && xMove != speed) {
            yMove = 0;
            xMove = -speed;
        }
        if (snakeBodyListSize == 0 && getHandler().getKeyManager().right) {
            yMove = 0;
            xMove = speed;
        } else if (getHandler().getKeyManager().right && xMove != -speed) {
            yMove = 0;
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics graphic) {
        String displayText = "Life: " + health + " Score: " + snakeBodyListSize + " HighestScore: " + highestScore;
        graphic.setFont(new Font("Serif", Font.BOLD, 24));
        graphic.setColor(Color.YELLOW);
        graphic.drawString(displayText, 20, 20);
        graphic.drawImage(Asset.player, (int) getEntityXPosition(), (int) getEntityYPosition(), getEntityWidth(), getEntityHeight(), null);
    }

    @Override
    public void hurt() {
        health--;
        determinePlayersHighestScore();
        snakeBodyList.clear();
        snakeBodyListSize = 0;
        Point coordinate = randomizeCoordinates();
        setEntityXPosition(coordinate.x);
        setEntityYPosition(coordinate.y);
        setCollisionBox();
        xMove = 0;
        yMove = 0;
        for (Entity entity: getHandler().getWorld().getEntityManager().getEntities()) {
            if (entity != this) {
                entity.setActive(false);
            }
        }
        if (health <= 0) {
            setActive(false);
            endGameForEntity();
        }
    }

    private void determinePlayersHighestScore() {
        if (snakeBodyListSize > highestScore) {
            highestScore = snakeBodyListSize;
        }
    }

    @Override
    public void endGameForEntity() {
        getHandler().endGame();
    }

    @Override
    public void move() {
        if (!checkEntityCollision(xMove, 0f)) {
            moveX();
        }

        if (!checkEntityCollision(0f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        int collisionUpperPosition = (int) (getEntityYPosition() + getCollisionBound().y) / getEntityHeight();
        int collisionLowerPosition = (int) (getEntityYPosition() + getCollisionBound().y + getCollisionBound().height) /getEntityHeight();

        if (xMove > 0) {
            int collisionXPosition = (int) ((getEntityXPosition() + xMove + getCollisionBound().x + getCollisionBound().width) / getEntityWidth()) + 1;

            if (!detectCollisionWithTile(collisionXPosition, collisionUpperPosition) && !detectCollisionWithTile(collisionXPosition, collisionLowerPosition)) {
                setEntityXPosition(getEntityXPosition() + xMove);
                setCollisionBox();
            } else {
                hurt();
            }
        } else if (xMove < 0) {
            int collisionXPosition = (int) (getEntityXPosition() + xMove + getCollisionBound().x) / getEntityWidth();

            if (!detectCollisionWithTile(collisionXPosition, collisionUpperPosition) && !detectCollisionWithTile(collisionXPosition, collisionLowerPosition)) {
                setEntityXPosition(getEntityXPosition() + xMove);
                setCollisionBox();
            } else {
                hurt();
            }
        }
    }

    public void moveY() {
        int collisionLeftPosition = (int) (getEntityXPosition() + getCollisionBound().x) / getEntityHeight();
        int collisionRightPosition = (int) (getEntityXPosition() + getCollisionBound().x + getCollisionBound().width) / getEntityWidth();

        if (yMove < 0) {
            int collisionYPosition = (int) (getEntityYPosition() + yMove + getCollisionBound().y) / getEntityHeight();

            if (!detectCollisionWithTile(collisionLeftPosition, collisionYPosition) && (!detectCollisionWithTile(collisionRightPosition, collisionYPosition))) {
                setEntityYPosition(getEntityYPosition() + yMove);
                setCollisionBox();
            } else {
                hurt();
            }

        } else if (yMove > 0) {
            int collisionYPosition = (int) ((getEntityYPosition() + yMove + getCollisionBound().y + getCollisionBound().height) / getEntityHeight()) + 1;

            if (!detectCollisionWithTile(collisionLeftPosition, collisionYPosition) && (!detectCollisionWithTile(collisionRightPosition, collisionYPosition))) {
                setEntityYPosition(getEntityYPosition() + yMove);
                setCollisionBox();
            } else {
                hurt();
            }
        }
    }

    public boolean detectCollisionWithTile(int x, int y) {
        return getHandler().getWorld().getTile(x, y).isSolid();
    }

    public void eat() {
        Followable followable;
        if (snakeBodyListSize == 0) {
            followable = this;
        } else {
            followable = snakeBodyList.get(snakeBodyListSize - 1);
        }
        SnakeBody snakeBody = new SnakeBody(getHandler(),followable.getPosition().x - xMove * DEFAULT_SNAKE_WIDTH,
                followable.getPosition().y - yMove * DEFAULT_SNAKE_HEIGHT, DEFAULT_SNAKE_WIDTH, DEFAULT_SNAKE_HEIGHT, followable) ;
        snakeBodyList.add(snakeBody);
        snakeBodyListSize = snakeBodyList.size();
        getHandler().getWorld().getEntityManager().addEntity(snakeBody);
    }

    @Override
    public Point getPosition() {
        return new Point((int) getEntityXPosition(), (int) getEntityYPosition());
    }

    public int getHighestScore() {
        return highestScore;
    }
}
