package com.yangjun.c2b.big2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CardPattern implements Comparable<CardPattern> {
	protected final List<Card> cards;
	
	public CardPattern (List<Card> cards) {
		Collections.sort(cards);
		this.cards = cards;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public Card getCard(int index) {
		return cards.get(index);
	}
	
	public int size() {
		return cards.size();
	}
	
	public String getPatternInfo() {
		String cardsStr = " " + cards.stream()
			    .map(Card::toString)
			    .collect(Collectors.joining(" "));
		return getCName() + cardsStr;
	}
	public abstract String getCName();
}
