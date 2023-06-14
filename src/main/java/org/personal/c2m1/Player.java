package org.personal.c2m1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Player  {
	String Name ;
	Stack<Card> handCard;
	Integer point;//分數
	//出牌
	public Card playCard(Stack<Card> handCard) {
		return handCard.pop();
	}
	public Player() {
		handCard = new Stack<Card>();
		point=0;
	}

	public Stack<Card> getHandCard() {
		return handCard;
	}
	public void setName(String name) {
		Name = name;
	}
	public abstract Stack<Card> drawCard(Deck deck,Stack<Card> playerHandCard);
}
