package com.yangjun.c2b.big2;

import java.util.List;

public class PairPattern extends CardPattern  {

	public PairPattern(List<Card> cards) {
		super(cards);
	}

	@Override
	public int compareTo(CardPattern c) {
		return getCard(1).compareTo(c.getCard(1));
	}

	@Override
	public String getCName() {
		return "對子";
	}

}
