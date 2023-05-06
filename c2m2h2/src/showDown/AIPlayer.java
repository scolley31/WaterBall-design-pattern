package showDown;

public class AIPlayer extends Player {
    protected AIPlayer(String name) {
        super(name);
    }
    public void nameHimself() {
       setName(name);
    }

    @Override
    protected Card showCard(Card card) {
        int index = (int) (Math.random() * getHand().getHandSize());
        Card showCard = getHand().getCard(index);
        this.getHand().getCards().remove(index);
        return showCard;
    }
}

