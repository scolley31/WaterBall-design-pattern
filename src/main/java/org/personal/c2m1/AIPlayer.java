package org.personal.c2m1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AIPlayer extends Player{

	private Stack<Card> handCard;

	public AIPlayer() {
		handCard=new Stack<>();
	}
	//直到所有人都擁有手牌 (Hand) 13 張牌為止。
	public void drawCard(Deck deck) {
		handCard.push(deck.drawCard(deck));
	}
}
