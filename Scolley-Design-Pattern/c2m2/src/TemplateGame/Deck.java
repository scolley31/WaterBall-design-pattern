package TemplateGame;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class Deck<Card> {

    private final Stack<Card> cardStack = new Stack<>();

    public Deck() {}

    public void shuffle() {
        Collections.shuffle(cardStack);
    }

    public Card drawCard() {
        return cardStack.pop();
    }

    public void addCard(Card card) {
        cardStack.add(card);
    }

    public void addCard(Collection<Card> cards) {
        cardStack.addAll(cards);
    }

    public int cardStackNumber() {
        return cardStack.size();
    }

    public Stack<Card> getCardStack() {
        return cardStack;
    }
}
