package Base.CardPatternType;

import Base.Card;
import Base.CardPattern;

import java.util.List;
import java.util.stream.Collectors;

public class Pair extends CardPattern {

    public Pair(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isSameCardPattern(CardPattern cardPattern) {
        return cardPattern instanceof Pair;
    }

    @Override
    protected int doCompare(CardPattern cardPattern) {
        int rankValue = this.getCards().get(0).getRank().getValue();
        int CardPatternRankValue = getCardPatternCards(cardPattern).get(0).getRank().getValue();

        int suitValue = this.getCards().stream()
                .sorted(Card::compareTo)
                .collect(Collectors.toList()).get(1).getSuit().getValue();

        int cardPatternSuitValue = getCardPatternCards(cardPattern).stream()
                .sorted(Card::compareTo)
                .collect(Collectors.toList()).get(1).getSuit().getValue();

        if (rankValue == CardPatternRankValue) {
            return suitValue - cardPatternSuitValue;
        }
        return rankValue - CardPatternRankValue;
    }
}
