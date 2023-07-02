package com.yangjun.c2b.big2;

import java.util.List;

public class FullhousePattern extends CardPattern  {

	public FullhousePattern(List<Card> cards) {
		super(cards);
	}

	@Override
	public int compareTo(CardPattern c) {
		Card thisMainCard = getMainCard();
		Card otherMainCard = ((FullhousePattern)c).getMainCard();
		return thisMainCard.compareTo(otherMainCard);
	}

	public Card getMainCard() {
		Card cardFist = cards.get(0);
		long cardFistCount = cards.stream().filter(card -> card.getRank() == cardFist.getRank()).count();

		if (cardFistCount == 3) {
			return cardFist;
		} else {
			return cards.stream().filter(card -> card.getRank() != cardFist.getRank()).findFirst().get();
		}
	}

	@Override
	public String getCName() {
		return "葫蘆";
	}
}
