package CardPatternHandle.cardPatternHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Pair;
import CardPatternHandle.CardPatternHandler;

import java.util.List;

public class PairHandler extends CardPatternHandler {
    public PairHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Card> cards) {
        if (cards.size() != 2) return false;
        return cards.get(0).getRank().getValue() == cards.get(1).getRank().getValue();
    }

    @Override
    protected CardPattern doHandler(List<Card> cards) {
        return new Pair(cards);
    }
}
