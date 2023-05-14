package showDown;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends Base.Deck<Card> {
    protected static Deck initShowDownDeck() {
        Deck deck = new Deck();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.addCard(new Card(suit, rank, deck));
            }
        }
        return deck;
    }
}
