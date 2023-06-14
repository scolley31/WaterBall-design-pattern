package org.personal.c2m1;

import java.util.Stack;

public class HumanPlayer extends Player {
	public HumanPlayer() {
		super();
	}
	//直到所有人都擁有手牌 (Hand) 13 張牌為止。
	public Stack<Card> drawCard(Deck deck,Stack<Card> playerHandCard) {
		playerHandCard.push(deck.drawCard(deck));
		return playerHandCard;
	}


}
