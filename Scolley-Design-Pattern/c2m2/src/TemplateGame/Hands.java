package TemplateGame;

import java.util.ArrayList;
import java.util.List;

public class Hands<Card> {

    private final List<Card> handCards = new ArrayList<>();

    public Hands() {
    }

    public void addHandCard(Card card) {
        handCards.add(card);
    }

    public int size() {
        return handCards.size();
    }

    public List<Card> getHandCards() {
        return handCards;
    }
}
