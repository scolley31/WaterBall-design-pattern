package OldUNO;

abstract public class Player {
    protected String name;
    protected UNO uno;
    protected Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setUNO(UNO uno) {
        this.uno = uno;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    abstract void nameHimself();

    abstract Card showCard();

    public void addHandCard(Card card) {
        hand.addCard(card);
        System.out.println("name " + name + " 手上還有 " + hand.size() + " 張牌");
    }

    public boolean canShowCard() {
        for (Card card: getHand().getCardList()) {
            if (Card.isTheSameType(card, uno.getCardOnTable())) return true;
        }
        return false;
    }
}
