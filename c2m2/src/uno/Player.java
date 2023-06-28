package uno;

import Base.Game;

public abstract class Player extends Base.Player<Card> {
    private Uno uno;
    public Player(String name) {
        super(name);
    }
    public abstract void nameHimself();

    @Override
    public String toString() {
        return name;
    }

    public Card takeTurn(Card currentCard) {
        Card card = showCard(currentCard);
        System.out.printf("player %s played %s\n", this.getName(), card.toString());
        return card;
    }
    protected boolean haveValidCard() {
        return getHand().getCards().stream().anyMatch(c->c.isValidCard(getUno().getTopCard()));
    }

    public void setUno(Uno uno) {
        this.uno = uno;
    }

    public Uno getUno() {
        return uno;
    }
}
