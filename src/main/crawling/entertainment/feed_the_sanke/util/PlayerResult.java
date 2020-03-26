package crawling.entertainment.feed_the_sanke.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private PlayerData currentPlayer;
    private List<PlayerData> rankingList = new ArrayList<>();

    public PlayerResult() {
    }

    public PlayerData getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerData currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<PlayerData> getRankingList() {
        return rankingList;
    }
}
