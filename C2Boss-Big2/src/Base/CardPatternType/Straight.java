package Base.CardPatternType;

import Base.Card;
import Base.CardPattern;

import java.util.*;

public class Straight extends CardPattern {

    public Straight(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isSameCardPattern(CardPattern cardPattern) {
        return cardPattern instanceof Straight;
    }

    @Override
    protected int doCompare(CardPattern cardPattern) {
        Card comparedBigestCard = getCardPatternCards(cardPattern).stream()
                .max(Card::compareTo)
                .get();
        Card comparingBigestCard = getCardPatternCards(this).stream()
                .max(Card::compareTo)
                .get();
        if (comparingBigestCard.getRank().getValue() == comparedBigestCard.getRank().getValue()) {
            return comparingBigestCard.getSuit().getValue() - comparedBigestCard.getSuit().getValue();
        }
        return comparingBigestCard.getRank().getValue() - comparedBigestCard.getRank().getValue();
    }

    public boolean checkIsA2345(List<Card> cards) {
        if (cards.size() != 5) return false;
        return cards.get(0).getRank().getValue() == 1
                && cards.get(1).getRank().getValue() == 2
                && cards.get(2).getRank().getValue() == 3
                && cards.get(3).getRank().getValue() == 12
                && cards.get(4).getRank().getValue() == 13;
    }

    public boolean checkIs23456(List<Card> cards) {
        if (cards.size() != 5) return false;
        return cards.get(0).getRank().getValue() == 1
                && cards.get(1).getRank().getValue() == 2
                && cards.get(2).getRank().getValue() == 3
                && cards.get(3).getRank().getValue() == 4
                && cards.get(4).getRank().getValue() == 13;

    }

    private boolean isStraight(List<Card> cards) {
        Set<Integer> cardValues = new HashSet<>();
        for (Card card : cards) {
            cardValues.add(card.getRank().getValue());
        }
        return cardValues.size() == 5 && Collections.max(cardValues) - Collections.min(cardValues) == 4;
    }
}
