package crawling.entertainment.feed_the_sanke.gfx;

import java.awt.image.BufferedImage;

public class Asset {

    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    public static BufferedImage player;
    public static BufferedImage dirt;
    public static BufferedImage snakeBody;
    public static BufferedImage stone;
    public static BufferedImage food;

    public static void init() {
        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        player = spriteSheet.crop(0, 0, WIDTH, HEIGHT);
        dirt = spriteSheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        snakeBody = spriteSheet.crop(2 * WIDTH, 0, WIDTH, HEIGHT);
        stone = spriteSheet.crop(3 * WIDTH, 0, WIDTH, HEIGHT);
        food = spriteSheet.crop(0, HEIGHT, WIDTH, HEIGHT);
    }
}
