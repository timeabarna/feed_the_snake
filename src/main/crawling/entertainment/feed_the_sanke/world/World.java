package crawling.entertainment.feed_the_sanke.world;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.entity.EntityManager;
import crawling.entertainment.feed_the_sanke.entity.food.Food;
import crawling.entertainment.feed_the_sanke.entity.snake.SnakeHead;
import crawling.entertainment.feed_the_sanke.tile.Tile;
import java.awt.Graphics;

public class World {

    private int width;
    private int height;
    private int[][] worldTiles;
    private Handler handler;
    private EntityManager entityManager;

    public World(Handler handler) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new SnakeHead(handler,100, 100));
        this.width = handler.getGameWidth();
        this.height = handler.getGameHeight();
        loadWorld();
        entityManager.addEntity(new Food(handler));
    }

    public  void gamePlay() {
        entityManager.gamePlay();
    }

    public void render(Graphics graphic) {
        for(int y = 0; y < height / Tile.TILE_HEIGHT + 1; y++) {
            for(int x = 0; x < width / Tile.TILE_WIDTH + 1; x++) {
                getTile(x, y).render(graphic, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
        entityManager.render(graphic);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.dirtTile;
        }
        Tile tile = Tile.getTileById(worldTiles[x][y]);
        if(tile == null) {
            return Tile.dirtTile;
        }
        return tile;
    }

    private void loadWorld() {
        worldTiles = new int[width / Tile.TILE_WIDTH + 1][height / Tile.TILE_HEIGHT + 1];

        for(int y = 1; y < height / Tile.TILE_HEIGHT - 1; y++) {
            for(int x = 1; x < width / Tile.TILE_WIDTH - 1; x++){
                worldTiles[x][y] = Tile.dirtTile.getId();
            }
        }
        createLeftBorder();
        createLowerBorder();
        createRightBorder();
        createUpperBorder();
    }

    private void createUpperBorder() {
        for(int y = 0; y < 1; y++) {
            for(int x = 0; x < width / Tile.TILE_HEIGHT; x++){
                worldTiles[x][y] = Tile.rockTile.getId();
            }
        }
    }

    private void createLowerBorder() {
        int heightForBorder;
        if (height % Tile.TILE_HEIGHT == 0) {
            heightForBorder = height / Tile.TILE_HEIGHT - 1;
        } else {
            heightForBorder = height / Tile.TILE_HEIGHT;
        }
        for(int y = heightForBorder; y <= height / Tile.TILE_HEIGHT; y++) {
            for(int x = 0; x <width / Tile.TILE_WIDTH; x++){
                worldTiles[x][y] = Tile.rockTile.getId();
            }
        }
    }

    private void createLeftBorder() {
        for(int y = 0; y < height / Tile.TILE_HEIGHT; y++) {
            for(int x = 0; x < 1; x++){
                worldTiles[x][y] = Tile.rockTile.getId();
            }
        }
    }

    private void createRightBorder() {
        int widthForBorder;
        if (width % Tile.TILE_WIDTH == 0) {
            widthForBorder = width / Tile.TILE_WIDTH - 1;
        } else {
            widthForBorder = width / Tile.TILE_WIDTH;
        }
        for(int y = 0; y < height / Tile.TILE_HEIGHT; y++) {
            for(int x = widthForBorder; x <= width / Tile.TILE_WIDTH; x++){
                worldTiles[x][y] = Tile.rockTile.getId();
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
