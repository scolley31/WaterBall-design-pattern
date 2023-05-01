public abstract class Player {
    private int point = 0;
    private String name;
    private Hand hand;
    private boolean hasSwapPrivilege = true;
    private ExchangeHands exchangeHand;

    public abstract void nameHimself();

    public abstract Card showCard();

    protected Player(String name) {
        this.name = name;
        addHand();
    }

    @Override
    public String toString() {
        return name;
    }

    public void gainPoint() {
        this.point++;
    }
    public void addHand() {
        Hand hand = new Hand(this);
        this.setHand(hand);
    }
    public Card takeTurn() {
        return showCard();
    }

    public void checkExchangeHandRound() {
        if (exchangeHand != null) {
            exchangeHand.setRound(exchangeHand.getRound() - 1);
            if (exchangeHand.getRound() == 0) {
                exchangeHand.swapBack();
                exchangeHand = null;
            }
        }
    }

    public void exchangeHands(Player otherPlayer) {
            exchangeHand = new ExchangeHands(this, otherPlayer);
            Hand temp = this.getHand();
            this.setHand(otherPlayer.getHand());
            otherPlayer.setHand(temp);
            this.setHasSwapPrivilege(false);
    }
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasSwapPrivilege(boolean hasSwapPrivilege) {
        this.hasSwapPrivilege = hasSwapPrivilege;
    }

    public boolean isHasSwapPrivilege() {
        return hasSwapPrivilege;
    }
    public int getPoint() {
        return point;
    }
}
