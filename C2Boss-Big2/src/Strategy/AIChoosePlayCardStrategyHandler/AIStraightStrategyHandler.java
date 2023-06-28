package Strategy.AIChoosePlayCardStrategyHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Straight;
import Base.Player;
import Base.Round;
import CardPatternHandle.cardPatternHandler.StraightHandler;
import Strategy.AIPlayCardStrategy;

import java.util.*;


public class AIStraightStrategyHandler extends AIPlayCardStrategy {
    private StraightHandler straightHandler = new StraightHandler(null);


    public AIStraightStrategyHandler(AIPlayCardStrategy next) {
        super(next);
    }

    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        List<Card> aiCards = player.getHandCards();
        aiCards.sort(Card::compareTo);
        if (has23456(aiCards)) {
            return play23456(aiCards).isBiggerThan(round.getTopPlay());
        }
        if (hasA2345(aiCards)) {
            return playA2345(aiCards).isBiggerThan(round.getTopPlay());
        }
        for (int i = 0; i < aiCards.size() - 4; i++) {
            List<Card> tempCards = aiCards.subList(i, i + 5);

            if (straightHandler.isSpecialStraight(tempCards)) {
                return true;
            }
            CardPattern convertCardPattern = straightHandler.convertCardPattern(tempCards);
            if (convertCardPattern != null && convertCardPattern.isBiggerThan(round.getTopPlay())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        List<Card> aiCards = player.getHandCards();
        aiCards.sort(Card::compareTo);

        if (hasA2345(aiCards)) {
            return playA2345(aiCards);
        } else if (has23456(aiCards)) {
            return play23456(aiCards);
        }
        for (int i = 0; i <= aiCards.size() - 4; i++) {
            List<Card> tempCards = new ArrayList<>(aiCards.subList(i, i + 5));
            CardPattern convertCardPattern = straightHandler.convertCardPattern(tempCards);
            if (convertCardPattern.isBiggerThan(round.getTopPlay())) {
                return convertCardPattern;
            }
        }
        return null;
    }

    private boolean hasA2345(List<Card> cards) {
        return cards.stream().anyMatch(card -> card.getRank().getValue() == 13) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 12) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 1) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 2) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 3);
    }

    private CardPattern playA2345(List<Card> cards) {
        Set<Integer> cardValues = new HashSet<>();
        List<Card> playedCards = new ArrayList<>();

        for (Card card : cards) {
            int value = card.getRank().getValue();
            if (value == 13 || value == 12 || value == 1 || value == 2 || value == 3) {
                if (!cardValues.contains(value)) {
                    playedCards.add(card);
                    cardValues.add(value);
                }
            }
        }
        return new Straight(playedCards);
    }

    private boolean has23456(List<Card> cards) {
        return cards.stream().anyMatch(card -> card.getRank().getValue() == 13) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 1) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 2) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 3) &&
                cards.stream().anyMatch(card -> card.getRank().getValue() == 4);
    }

    private CardPattern play23456(List<Card> cards) {
        Set<Integer> cardValues = new HashSet<>();
        List<Card> playedCards = new ArrayList<>();

        for (Card card : cards) {
            int value = card.getRank().getValue();
            if (value == 13 || value == 4 || value == 1 || value == 2 || value == 3) {
                if (!cardValues.contains(value)) {
                    playedCards.add(card);
                    cardValues.add(value);
                }
            }
        }
        return new Straight(playedCards);
    }
}
