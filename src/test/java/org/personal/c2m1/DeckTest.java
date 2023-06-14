package org.personal.c2m1;


import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.personal.c2m1.Card.Rank;
import org.personal.c2m1.Card.Suit;

class DeckTest {
	@Test
	void test_Card() {
		Card card=new Card(Arrays.stream(Rank.values()).findAny().get(),
				Arrays.stream(Suit.values()).findAny().get());
		System.out.println(card.toString());
	}
	@Test
	void test_Deck() {
		Deck deck=new Deck();
		for (Card card : deck.getDeck()){
			System.out.println(card.toString());
		}
	}
}
