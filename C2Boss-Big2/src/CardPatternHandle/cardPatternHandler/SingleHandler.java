package CardPatternHandle.cardPatternHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Single;
import CardPatternHandle.CardPatternHandler;

import java.util.List;

public class SingleHandler extends CardPatternHandler {
    public SingleHandler(CardPatternHandler next) {
        super(next);
    }
    @Override
    protected boolean match(List<Card> cards) {
        return cards.size() == 1;
    }
    @Override
    protected CardPattern doHandler(List<Card> cards) {
        return new Single(cards);
    }
}
