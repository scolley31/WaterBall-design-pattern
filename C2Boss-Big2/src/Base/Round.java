package Base;

import java.util.List;

public class Round {
    private CardPattern topPlay;
    private Player topPlayer;
    private int turn;
    private boolean isFirstRound = false;

    public boolean isThreePlayerPass(List<Player> players) {
        return players.stream()
                .filter(player -> player.isPass())
                .count() == 3;
    }

    public void setTopPlay(CardPattern topPlay) {
        this.topPlay = topPlay;
    }

    public void setTopPlayer(Player topPlayer) {
        this.topPlayer = topPlayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Player getTopPlayer() {
        return topPlayer;
    }

    public CardPattern getTopPlay() {
        return topPlay;
    }

    public boolean isFirstRound() {
        return isFirstRound;
    }

    public void setFirstRound(boolean firstRound) {
        isFirstRound = firstRound;
    }

    protected void update(CardPattern cardPattern, Player player) {
        if (isFirstRound) {
            this.setFirstRound(false);
        }
        if (cardPattern != null) {
            this.setTopPlayer(player);
            this.setTopPlay(cardPattern);
        }
        this.setTurn(this.getTurn() + 1);
    }

    public boolean checkIsValidOperation(CardPattern cardPattern, Player player) {
            //如果目前是第一回合，確認玩家打出的牌有沒有包含梅花三
        if (isFirstRound && !cardPattern.containsClub3()) {
            System.out.println("此牌型不合法，請在嘗試一次。");
            return false;
        }
            // 確認玩家打出的牌是否有大於頂牌，或是是不是打出合理的牌型
        if (cardPattern == null && getTopPlay() != null && !player.isPass()) {
            System.out.println("此牌型不合法，請在嘗試一次。");
            return false;
        }
            // 確認玩家有沒有在新的回合中，沒有頂牌的情況下，是否有喊出Pass
        if (this.getTopPlay() == null && cardPattern == null) {
            System.out.println("你不能在新的回合Pass.");
            return false;
        }
        return true;
    }
}
