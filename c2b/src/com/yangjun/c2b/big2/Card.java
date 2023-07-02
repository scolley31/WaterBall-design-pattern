package com.yangjun.c2b.big2;

public class Card implements Comparable<Card> {
	
	private final Suit suit;
	private final Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int compareTo(Card c) {
		if (this.getRank() == c.getRank()) {
			return this.getSuit().compareTo(c.getSuit());
		} else {
			return this.getRank().compareTo(c.getRank());
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s]", suit, rank);
	}
	
	public enum Suit {
		CLUB('C'), DIAMOND('D'), HEART('H'), SPADE('S');

		private final char representation;

		Suit (char c) {
			this.representation = c;
		}

		@Override
		public String toString() {
			return String.valueOf(representation);
		}
	}
	
	public enum Rank {
		THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), 
		NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A"), TWO("2");
		
		private final String representation;
		
		private Rank(String representation) {
			this.representation = representation;
		}
		
		@Override
		public String toString() {
			return String.valueOf(representation);
		}
	}
}
