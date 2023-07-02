package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
    	cards.add(card);
    }

	public List<Card> getCards() {
		return cards;
	}
	
	public int size() {
		return cards.size();
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
}
