package org.personal.c2m1;

import java.util.Collections;
import java.util.Stack;
import org.personal.c2m1.Card.Rank;
import org.personal.c2m1.Card.Suit;
/*
* 牌堆中一開始存有 52 張牌 (Card)。
* 每張牌都會擁有階級 (Rank) 及花色 (Suit)。
* */
public class Deck {
	private Stack<Card> deck;//牌 (Card)
	public Stack<Card> getDeck() {
		return deck;
	}
	public Deck() {
		init();
		Collections.shuffle(this.deck);
	}

	private void init() {
		this.deck= new Stack<>();
		for (Rank rank : Rank.values()){
			for (Suit suit : Suit.values() ){
				this.deck.add(new Card(rank,suit));
			}
		}
	}

	public Card drawCard(Deck deck){
		return deck.getDeck().pop();
	}
	//牌堆會進行洗牌 (Shuffle)。
	public void shuffle(){
		Collections.shuffle(this.deck);
	}

}
