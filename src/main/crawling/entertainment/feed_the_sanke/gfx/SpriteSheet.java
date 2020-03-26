package crawling.entertainment.feed_the_sanke.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage image) {
        this.spriteSheet = image;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
