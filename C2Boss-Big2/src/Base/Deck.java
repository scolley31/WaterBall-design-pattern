package Base;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public Deck() {
        initDeck();
    }

    public void deal(Player player) {
        player.addCardToHandCards(cards.pop());
    }

    void initDeck() {
        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                cards.push(new Card(rank, suit));
            }
        }
    }

    void shuffle() {
        Collections.shuffle(cards);
    }
    boolean isEmpty(){
        return cards.isEmpty();
    }
}
