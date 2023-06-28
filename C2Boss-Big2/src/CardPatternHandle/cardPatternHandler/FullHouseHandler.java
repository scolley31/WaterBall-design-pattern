package CardPatternHandle.cardPatternHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.FullHouse;
import CardPatternHandle.CardPatternHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullHouseHandler extends CardPatternHandler {
    public FullHouseHandler(CardPatternHandler next) {
        super(next);
    }

    @Override
    protected boolean match(List<Card> cards) {
        if (cards.size() != 5) return false;
        boolean isThreeSameCards = countMap(cards).values().stream()
                .anyMatch(count -> count == 3);
        boolean isPair = countMap(cards).values().stream()
                .anyMatch(count -> count == 2);
        return isThreeSameCards && isPair;
    }

    @Override
    protected CardPattern doHandler(List<Card> cards) {
        return new FullHouse(cards);
    }

    private Map<Integer, Integer> countMap(List<Card> cards) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : cards) {
            countMap.put(card.getRank().getValue(), countMap.getOrDefault(card.getRank().getValue(), 0) + 1);
        }
        return countMap;
    }
}
