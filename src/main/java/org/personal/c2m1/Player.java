package org.personal.c2m1;

import java.util.Stack;

public abstract class Player  {
	public String getName() {
		return Name;
	}

	protected Stack<Card> handCard;
	protected Integer point;//分數
	protected int exchangeHandsPrivilege;//交換手牌特權數
	protected String Name ;



	public Player() {
		handCard = new Stack<Card>();
		point=0;
		exchangeHandsPrivilege=1;
		Name="player";
	}

	public abstract Stack<Card> drawCard(Deck deck,Stack<Card> playerHandCard);

	public Stack<Card> getHandCard() {
		return handCard;
	}
	public void setName(String name) {
		Name = name;
	}
	//出牌
	public Card playCard(Stack<Card> handCard) {
		return handCard.pop();
	}
	@Override
	public String toString() {
		return "Player{" + "handCard=" + handCard + ", point=" + point + ", exchangeHandsPrivilege="
				+ exchangeHandsPrivilege + ", Name='" + Name + '\'' + '}';
	}
}
