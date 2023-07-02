package com.yangjun.c2b.big2;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Player {
	private final Hand hand = new Hand();
	private String name;
	private Big2 game;
	
	public Big2 getGame() {
		return game;
	}
	public void setGame(Big2 game) {
		this.game = game;
	}

	public void addHandCard(Card card) {
		hand.addCard(card);
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public List<Card> getHandCards() {
		return hand.getCards();
	}
	
	public List<Card> getHandCardsByIndexs(List<Integer> indexs) {
		List<Card> handCards = getHandCards();
		return indexs.stream().map(handCards::get).collect(Collectors.toList());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasNoHandCard() {
		return hand.isEmpty();
	}
	
	public abstract void nameHimSelf(int index);
	
	public TurnDecison takeTurn() {
		printCardSelections();
		return makeTurnDecison();
	}
	
	public abstract TurnDecison makeTurnDecison();
	
	public void printCardSelections() {
		List<Card> cards = hand.getCards();
		StringBuilder cardsRepresentation = new StringBuilder();
		StringBuilder numberRepresentation = new StringBuilder();
		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			numberRepresentation.append(rightPadding(i, card.getRank().toString().length() + 4));
			cardsRepresentation.append(card).append(" ");
		}
		System.out.println(numberRepresentation.toString());
		System.out.println(cardsRepresentation.toString());
	}
	
	private String rightPadding(int number, int length) {
		return String.format("%-" + length + "s", Integer.toString(number));
	}
}
