package com.yangjun.c2b.big2;

import java.util.List;

public class StraightPattern extends CardPattern {

	public StraightPattern(List<Card> cards) {
		super(cards);
	}

	@Override
	public int compareTo(CardPattern c) {
		return getCard(size() - 1).compareTo(c.getCard(c.size() - 1));
	}

	@Override
	public String getCName() {
		return "順子";
	}
}
