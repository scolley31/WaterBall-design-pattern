package designpattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> cards;

	public Deck() {
		List<Card> cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(rank, suit);
				cards.add(card);
			}

		}
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	public Card drawCard() {
		Card getCard = this.cards.get(0);
		this.cards.remove(0);
		return getCard;
	}

}
