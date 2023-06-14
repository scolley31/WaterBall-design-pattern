package org.personal.c2m1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Player  {
	String Name ;
	Stack<Card> handCard;
	public Player() {
		handCard = new Stack<Card>();
	}

	public Stack<Card> getHandCard() {
		return handCard;
	}
	public void setName(String name) {
		Name = name;
	}
	public abstract void drawCard(Deck deck);
}
