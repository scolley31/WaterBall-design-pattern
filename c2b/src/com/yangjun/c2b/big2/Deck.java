package com.yangjun.c2b.big2;

import java.util.Collections;
import java.util.Stack;

import com.yangjun.c2b.big2.Card.Rank;
import com.yangjun.c2b.big2.Card.Suit;

public class Deck {
	private final Stack<Card> cards = new Stack<>();
	
	public Deck() {
		Rank[] ranks = Card.Rank.values();
		Suit[] suits = Card.Suit.values();
		for (Rank rank : ranks) {
			for (Suit suit : suits) {
				cards.push(new Card(suit, rank));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Stack<Card> getCards() {
		return cards;
	}

	public int size() {
		return cards.size();
	}
	
	public boolean empty() {
		return cards.empty();
	}
	
	public Card deal() {
		return cards.pop();
	}
}
