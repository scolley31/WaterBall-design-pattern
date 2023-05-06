package Base;

public abstract class Player<Card> {
    protected String name;
    protected Hand<Card> hand = new Hand<>();
    public Player(String name) {
        this.name = name;
    }

    public abstract void nameHimself();

    protected abstract Card showCard(Card card);

    public void addHandCard(Card card) {
        this.hand.addCard(card);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand<Card> getHand() {
        return hand;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }


}
