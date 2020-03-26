package crawling.entertainment.feed_the_sanke.util;

import java.io.Serializable;

public class PlayerData implements Serializable, Comparable<PlayerData> {

    private static final long serialVersionUID = 1L;

    private String playerName;
    private int highestScore;

    public PlayerData(String playerName, int highestScore) {
        this.playerName = playerName;
        this.highestScore = highestScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHighestScore() {
        return highestScore;
    }

    @Override
    public int compareTo(PlayerData o) {
        return Integer.valueOf(Integer.valueOf(o.highestScore)).compareTo(this.getHighestScore());
    }
}
