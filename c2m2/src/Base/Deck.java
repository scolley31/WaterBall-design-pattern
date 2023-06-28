package Base;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck<Card> {
    protected final Stack<Card> cards = new Stack<>();

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card drawCard() {
        return cards.pop();
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void addCards(List<Card> cards) {
        cards.addAll(cards);
    }
    public void clear(){
        cards.clear();
    }
}
