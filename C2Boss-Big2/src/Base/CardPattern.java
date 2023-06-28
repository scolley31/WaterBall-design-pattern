package Base;

import java.util.List;

public abstract class CardPattern {
    private List<Card> cards;

    public CardPattern(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBiggerThan(CardPattern cardPattern) {
        if (isSameCardPattern(cardPattern)) {
            return doCompare(cardPattern) > 0;
        }
        return false;
    }

    public abstract boolean isSameCardPattern(CardPattern cardPattern);

    protected abstract int doCompare(CardPattern cardPattern);

    public List<Card> getCards() {
        return cards;
    }

    public static List<Card> getCardPatternCards(CardPattern cardPattern) {
        return cardPattern.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardPattern that = (CardPattern) o;
        if (((CardPattern) o).getCards().size() != this.getCards().size()) return false;

        return this.getCards().containsAll(((CardPattern) o).getCards());
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }

    public boolean containsClub3() {
        return cards.stream().anyMatch(card -> card.equals(new Card(Card.Rank.THREE, Card.Suit.CLUB)));
    }
}
