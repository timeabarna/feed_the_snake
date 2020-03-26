package crawling.entertainment.feed_the_sanke.entity;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.entity.snake.SnakeHead;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {

    private Handler handler;
    private SnakeHead player;
    private List<Entity> entities;

    public EntityManager(Handler handler, SnakeHead player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void gamePlay() {
        List<Entity> temporaryEntityList = new ArrayList<>(entities);
        Iterator<Entity> iterator =temporaryEntityList.iterator();
        while(iterator.hasNext()){
            Entity entity = iterator.next();
            entity.gamePlay();
            if (!entity.isActive()) {
                entities.remove(entity);
            }
        }
    }

    public void render(Graphics graphic) {
        for (Entity entity: entities) {
            entity.render(graphic);
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public SnakeHead getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
