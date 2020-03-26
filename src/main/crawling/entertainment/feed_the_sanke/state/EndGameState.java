package crawling.entertainment.feed_the_sanke.state;

import crawling.entertainment.feed_the_sanke.Handler;
import crawling.entertainment.feed_the_sanke.util.PlayerData;
import crawling.entertainment.feed_the_sanke.util.PlayerResult;
import crawling.entertainment.feed_the_sanke.util.Util;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

public class EndGameState extends State {

    private PlayerResult playerResult;
    private boolean stateSet = false;
    private boolean newRecord = false;

    public EndGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void gamePlay() {
        if (!stateSet) {
            playerResult = Util.readFileToPlayerResult();
            newRecord = isNewRecord();
            if (newRecord) {
                calculateNewRankingOrder();
            }
            playerResult.setCurrentPlayer(new PlayerData(handler.getUserName(), handler.getWorld().getEntityManager().getPlayer().getHighestScore()));
            Util.writePlayerResultToFile(playerResult);
            stateSet = true;
        }

    }

    @Override
    public void render(Graphics graphic) {
        if (stateSet) {
            graphic.setFont(new Font("Serif", Font.BOLD, 24));
            int yTextPosition = 20;
            graphic.drawString("Game Over. Thanks for playing.", 20, yTextPosition = yTextPosition + 25);

            if (newRecord) {
                graphic.drawString("New record!!!", 20, yTextPosition = yTextPosition + 25);
            }
            graphic.drawString("In this game your highest score was " + handler.getWorld().getEntityManager().getPlayer().getHighestScore(), 20, yTextPosition = yTextPosition + 25);
            graphic.drawString("", 20, yTextPosition = yTextPosition + 25);
            graphic.drawString("Our first 10 best Players: ", 20, yTextPosition = yTextPosition + 25);


            for (int i = 0; i < playerResult.getRankingList().size(); i++) {
                PlayerData playerData = playerResult.getRankingList().get(i);
                graphic.drawString((String.format("%02d", i + 1)) + "   " + playerData.getPlayerName() + "   " + playerData.getHighestScore(), 20, yTextPosition = yTextPosition + 25);
            }
        }
    }

    private boolean isNewRecord() {
        int storedScore = 0;
        if (playerResult.getCurrentPlayer() != null) {
            storedScore = playerResult.getCurrentPlayer().getHighestScore();
        }
        if (handler.getWorld().getEntityManager().getPlayer().getHighestScore() > storedScore) {
            return true;
        }
        return false;
    }

    private void calculateNewRankingOrder() {
        PlayerData playerData = new PlayerData(handler.getUserName(), handler.getWorld().getEntityManager().getPlayer().getHighestScore());
        List<PlayerData> rankingList = playerResult.getRankingList();
        rankingList.add(playerData);
        Collections.sort(rankingList);

        if(rankingList.size() == 11) {
            rankingList.remove(10);
        }
    }
}
