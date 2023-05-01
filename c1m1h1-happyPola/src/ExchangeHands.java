public class ExchangeHands {
    private int round = 3;
    private final Player exchanger;
    private final Player exchangee;

    public ExchangeHands(Player exchanger, Player exchangee) {
        this.exchanger = exchanger;
        this.exchangee = exchangee;
    }
    public void swapBack() {
            Hand temp = exchanger.getHand();
            exchanger.setHand(exchangee.getHand());
            exchangee.setHand(temp);
        System.out.println("After swapping back, the hands are:"+ exchanger.getHand().getCards().toString());
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }


}
