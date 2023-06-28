package CardPatternHandle.cardPatternHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Straight;
import CardPatternHandle.CardPatternHandler;

import java.util.*;

public class StraightHandler extends CardPatternHandler {
    private Set<Integer> cardValues = new HashSet<>();

    public StraightHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Card> cards) {
        if (cards.size() != 5) return false;
        cards.sort(Card::compareTo);
        return isSpecialStraight(cards) || isStraight(cards);
    }

    @Override
    protected CardPattern doHandler(List<Card> cards) {
        return Optional.ofNullable(specialStraight(cards)).orElse(new Straight(cards));
    }

    public CardPattern specialStraight(List<Card> cards) {
        if (QKA23(cards)) {
            return new Straight(cards);
        }
        if (KA234(cards)) {
            return new Straight(cards);
        }
        if (isA2345(cards)) {
            return new Straight(cards);
        }
        if (is23456(cards)) {
            return new Straight(cards);
        }
        return null;
    }

    public boolean isSpecialStraight(List<Card> cards) {
        return QKA23(cards) || KA234(cards) || isA2345(cards) || is23456(cards);
    }

    private boolean KA234(List<Card> cards) {
        return cards.get(0).getRank().getValue() == 1
                && cards.get(1).getRank().getValue() == 2
                && cards.get(2).getRank().getValue() == 11
                && cards.get(3).getRank().getValue() == 12
                && cards.get(4).getRank().getValue() == 13;
    }

    private boolean QKA23(List<Card> cards) {
        return cards.get(0).getRank().getValue() == 1
                && cards.get(1).getRank().getValue() == 10
                && cards.get(2).getRank().getValue() == 11
                && cards.get(3).getRank().getValue() == 12
                && cards.get(4).getRank().getValue() == 13;
    }

    private boolean isA2345(List<Card> cards) {
        return cards.get(0).getRank().getValue() == 1
                && cards.get(1).getRank().getValue() == 2
                && cards.get(2).getRank().getValue() == 3
                && cards.get(3).getRank().getValue() == 12
                && cards.get(4).getRank().getValue() == 13;
    }

    private boolean is23456(List<Card> cards) {
        return cards.get(0).getRank().getValue() == 1
                && cards.get(1).getRank().getValue() == 2
                && cards.get(2).getRank().getValue() == 3
                && cards.get(3).getRank().getValue() == 4
                && cards.get(4).getRank().getValue() == 13;

    }

    private boolean isStraight(List<Card> cards) {
        for (Card card : cards) {
            cardValues.add(card.getRank().getValue());
        }
        return cardValues.size() == 5 && Collections.max(cardValues) - Collections.min(cardValues) == 4;
    }
}
