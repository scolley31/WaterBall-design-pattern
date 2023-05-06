package uno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck extends Base.Deck {

    protected static Deck initUnoDeck() {
        Deck deck = new Deck();
        for (uno.Card.Number number : uno.Card.Number.values()) {
            for (uno.Card.Color color : uno.Card.Color.values()) {
                deck.addCard(new Card(color, number, deck));
            }
        }
        return deck;
    }
    protected Card drewCard(){
        return (Card) cards.pop();
    }
}

