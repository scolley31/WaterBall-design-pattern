import java.util.List;

public class ExchangeHands {
    private final Player left;
    private final Player right;
    private final Game game;
    private int round = 0;

    public ExchangeHands(Game game, Player left, Player right) {
        this.game = game;
        this.left = left;
        this.right = right;
        swap();
    }

    public void reexchangeHands() {
        System.out.printf("\u001B[32m" + "【系統】三回合已過，玩家 %s 與玩家 %s 重新換回了手牌。\n" + "\u001B[0m", left.getName(), right.getName());
        swap();

        left.removeExchangeHands(this);
        right.removeExchangeHands(this);
        game.removeExchangeHands(this);
    }

    private void swap() {
        List<Hand> hands = left.getHands();
        left.setHands(right.getHands());
        right.setHands(hands);
    }

    public void increaseRound() {
        round++;
    }

    public int getRound() {
        return round;
    }
}
