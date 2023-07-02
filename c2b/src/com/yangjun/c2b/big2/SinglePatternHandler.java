package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SinglePatternHandler extends CardPatternHandler {

	public SinglePatternHandler(CardPatternHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public boolean isValidPattern(List<Card> cards) {
		return cards.size() == 1;
	}

	@Override
	public CardPattern combinationCardPattern(List<Card> cards) {
		return new SinglePattern(cards);
	}

	@Override
	public boolean isValidClassType(CardPattern cardPattern) {
		return cardPattern instanceof SinglePattern;
	}

	@Override
	public CardPattern getSuitablePattern(CardPattern cardPattern, List<Card> palyerHandCards) {
		Card mainCard = cardPattern.getCard(0);
		Optional<Card> cardOp = palyerHandCards.stream().filter(c -> c.compareTo(mainCard) > 0).findFirst();
		List<Card> result = new ArrayList<>();
		if (cardOp.isPresent()) {
			result.add(cardOp.get());
			return new SinglePattern(result);
		} else {
			return null;
		}
		
	}

}
