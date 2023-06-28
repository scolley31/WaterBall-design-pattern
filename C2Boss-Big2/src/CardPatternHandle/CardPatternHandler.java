package CardPatternHandle;

import Base.Card;
import Base.CardPattern;

import java.util.List;

public abstract class CardPatternHandler {
    private CardPatternHandler next;

    public CardPatternHandler(CardPatternHandler next) {
        this.next = next;
    }

    public CardPattern convertCardPattern(List<Card> cards) {
        if (match(cards)) {
            return doHandler(cards);
        } else if (next != null) {
            return next.convertCardPattern(cards);
        }
        return null;
    }

    protected abstract boolean match(List<Card> cards);

    protected abstract CardPattern doHandler(List<Card> cards);
}
