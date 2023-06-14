package org.personal.c2m1;/*
 * 撲克牌比大小
 * https://people.cs.pitt.edu/~lipschultz/cs445/assign01/
 */
public class Card implements Comparable<Card>{

	/* These are enum types that make the suits and ranks of the cards more
	 * readable.  They are also used for comparison of cards, as shown in
	 * equals and compareTo below.  You can access the types themselves using
	 * the class, which you will need to do to initialize your deck of cards.
	 * See A1Help.java for a demo of this.
	 */
	enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS;}
	enum Rank {	TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;}
	private Rank rank;//階級 (Rank)
	private Suit suit;//花色 (Suit)

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public String toString()
	{
		return (rank + "-of-" + suit);
	}

	/** Compare this object to another Card.  Cards of the same rank are
	 *  considered equal, regardless of the suit.  Note that this differs
	 *  from the equals method.  Java enum types are automatically Comparable,
	 *  based on the order in which the values are defined (smallest to
	 *  largest).
	 */
	@Override
	public int compareTo(Card rhs) {
		return rank.compareTo(rhs.rank);
	}

	/** Equals is only true if both the suit and the rank of the cards match
	 */
	public boolean equals(Object rhs)
	{
		Card rside = (Card) rhs;
		if (suit == rside.suit && rank == rside.rank)
			return true;
		else
			return false;
	}
}
