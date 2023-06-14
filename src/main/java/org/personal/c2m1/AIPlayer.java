package org.personal.c2m1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AIPlayer extends Player{

	public AIPlayer() {
		super();
	}
	//直到所有人都擁有手牌 (Hand) 13 張牌為止。
	public Stack<Card> drawCard(Deck deck,Stack<Card> playerHandCard) {
		playerHandCard.push(deck.drawCard(deck));
		return playerHandCard;
	}
	public Stack<Card> getHandCard() {
		return this.handCard;
	}
}
