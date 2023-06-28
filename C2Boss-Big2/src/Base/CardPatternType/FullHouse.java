package Base.CardPatternType;

import Base.Card;
import Base.CardPattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullHouse extends CardPattern {

    public FullHouse(List<Card> cards) {
        super(cards);
    }

    public boolean isSameCardPattern(CardPattern cardPattern) {
       return cardPattern instanceof FullHouse;
    }

    @Override
    protected int doCompare(CardPattern cardPattern) {
        Integer thisValue = getThreeSameCardRankValue(getCountMap(this.getCards()));
        Integer cardPatternValue = getThreeSameCardRankValue(getCountMap(getCardPatternCards(cardPattern)));
        return thisValue - cardPatternValue;
    }


    private static Map<Integer, Integer> getCountMap(List<Card> cards) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : cards) {
            countMap.put(card.getRank().getValue(), countMap.getOrDefault(card.getRank().getValue(), 0) + 1);
        }
        return countMap;
    }

    private static Integer getThreeSameCardRankValue(Map<Integer, Integer> countMap) {
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 3) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
