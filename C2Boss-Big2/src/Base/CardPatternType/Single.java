package Base.CardPatternType;

import Base.Card;
import Base.CardPattern;

import java.util.List;

public class Single extends CardPattern {

    public Single(List<Card> cards) {
        super(cards);
    }

    public boolean isSameCardPattern(CardPattern cardPattern) {
        return cardPattern instanceof Single;
    }

    @Override
    protected int doCompare(CardPattern cardPattern) {
        return this.getCards().get(0).compareTo(cardPattern.getCards().get(0));
    }
}
