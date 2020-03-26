package crawling.entertainment.feed_the_sanke.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Tile {

    private static Tile[] tiles = new Tile[256];
    public static final Tile dirtTile = new DirtTile(0);
    public static final Tile rockTile = new RockTile(1);

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    private BufferedImage image;
    private final int id;

    public Tile(BufferedImage image, int id) {
        this.image = image;
        this.id = id;
        tiles[id] = this;
    }

    public void render(Graphics graphic, int x, int y) {
        graphic.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public static Tile getTileById(int id) {
        return tiles[id];
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}
