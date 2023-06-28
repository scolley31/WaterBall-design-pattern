package uno;

import java.util.List;

public class AIPlayer extends Player {
    protected AIPlayer(String name) {
        super(name);
    }

    @Override
    public void nameHimself() {
        setName(name);
    }

    @Override
    protected Card showCard(Card card) {
        List<Card> cards = getHand().getCards();

        Card playedCard = cards.stream()
                .filter(handCard -> handCard.isValidCard(this.getUno().getTopCard()))
                .findFirst().orElse(null);

        getHand().removeCard(playedCard);
        return playedCard;
    }
}
