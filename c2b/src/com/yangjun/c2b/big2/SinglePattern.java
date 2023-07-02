package com.yangjun.c2b.big2;

import java.util.List;

public class SinglePattern extends CardPattern {
	public SinglePattern(List<Card> cards) {
		super(cards);
	}

	@Override
	public int compareTo(CardPattern c) {
		return getCard(0).compareTo(c.getCard(0));
	}

	@Override
	public String getCName() {
		return "單張";
	}
}
