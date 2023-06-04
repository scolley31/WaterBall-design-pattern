package TemplateGame;

abstract public class AbstractPlayer<Card> {

    protected String name;

    protected Game<Card> game;
    protected final Hands<Card> hands = new Hands<>();

    protected abstract void nameHimself();

    public abstract Card showCard();

    public void addHandCard(Card card) {
        hands.addHandCard(card);
    }

    public Hands<Card> getHands() {
        return hands;
    }

    public String getName() {
        return name;
    }

    public Game<Card> getGame() {
        return game;
    }

    public void setGame(Game<Card> game) {
        this.game = game;
    }
}
