package Base;

import java.util.ArrayList;
import java.util.List;

public class Hand<Card> {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public int getHandSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
