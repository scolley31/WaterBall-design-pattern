package org.personal.c2m1;

import java.util.Stack;

public class HumanPlayer extends Player {
	private Stack<Card> handCard;

	public HumanPlayer() {
		handCard=new Stack<>();
	}
	//直到所有人都擁有手牌 (Hand) 13 張牌為止。
	public void drawCard(Deck deck) {
		handCard.push(deck.drawCard(deck));
	}
}
