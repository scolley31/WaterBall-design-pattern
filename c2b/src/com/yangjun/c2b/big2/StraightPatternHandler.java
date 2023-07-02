package com.yangjun.c2b.big2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StraightPatternHandler extends CardPatternHandler {

	public StraightPatternHandler(CardPatternHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public boolean isValidPattern(List<Card> cards) {
		return cards.size() == 5 && isSequentialCards(cards);
	}

	@Override
	public CardPattern combinationCardPattern(List<Card> cards) {
		return new StraightPattern(cards);
	}

	private boolean isSequentialCards(List<Card> cards) {
		int allRankCount = Card.Rank.values().length;
		int[] counts = new int[allRankCount];
		for (Card card : cards) {
			int rankCount = counts[card.getRank().ordinal()];
			if (rankCount > 0) {
				return false;
			} else {
	            counts[card.getRank().ordinal()] = rankCount + 1;
			}
		}

		for (int i = 0; i < allRankCount; i++) {
			boolean allNonZero = true;
			for (int j = 0; j < 5; j++) {
				if (counts[(i + j) % allRankCount] == 0) {
					allNonZero = false;
					break;
				}
			}
			if (allNonZero) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValidClassType(CardPattern cardPattern) {
		return cardPattern instanceof StraightPattern;
	}

	@Override
	public CardPattern getSuitablePattern(CardPattern cardPattern, List<Card> palyerHandCards) {
		Card mainCard = cardPattern.getCard(4);
		List<Card> biggerThanMainCardList = palyerHandCards.stream()
			.filter(c -> c.compareTo(mainCard) < 0)
			.collect(Collectors.toList());
		if (biggerThanMainCardList.size() < 5) {
			return null;
		}
		
		int allRankCount = Card.Rank.values().length;
		int[] counts = new int[allRankCount];
		for (Card card : biggerThanMainCardList) {
			int rankCount = counts[card.getRank().ordinal()];
	        counts[card.getRank().ordinal()] = rankCount + 1;
		}		
		
	    for (int i = 0; i <= allRankCount - 5; i++) {
	        if (counts[i] > 0 && counts[i + 1] > 0 && counts[i + 2] > 0 && counts[i + 3] > 0 && counts[i + 4] > 0) {
	            int finalI = i;
	            List<Card> straightCards = biggerThanMainCardList.stream()
	                .filter(c -> c.getRank().ordinal() >= finalI && c.getRank().ordinal() <= finalI + 4)
	                .collect(Collectors.toList());
	            return new StraightPattern(straightCards);
	        }
	    }

		return null;
	}
}
