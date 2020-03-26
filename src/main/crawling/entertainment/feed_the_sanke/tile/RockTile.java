package crawling.entertainment.feed_the_sanke.tile;

import crawling.entertainment.feed_the_sanke.gfx.Asset;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(Asset.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
