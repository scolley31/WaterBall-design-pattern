import java.util.List;

public class ExchangeHands {

    public final static int TURN_COUNT_DOWN = 3;
    private Player exchanger;
    private Player exchangee;
    private int turnCount = 1;

    public int getTurnCount() {
        return turnCount;
    }

    public ExchangeHands(Player exchanger, Player exchangee) {
        this.exchanger = exchanger;
        this.exchangee = exchangee;
    }

    public void swapHands() {
        List<Card> temp = exchangee.getHands();
        exchangee.setHands(exchanger.getHands());
        exchanger.setHands(temp);
    }

    public void restoreHands() {
        List<Card> temp = exchanger.getHands();
        exchanger.setHands(exchangee.getHands());
        exchangee.setHands(temp);
    }

    public void incTurnCount() {
        turnCount += 1;
    }
}
