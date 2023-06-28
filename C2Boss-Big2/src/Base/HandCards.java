package Base;

import Base.Card;
import Base.CardPatternType.Single;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HandCards {
    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card... card) {
        cards.addAll(List.of(card));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
    public void sort(){
        this.cards.sort(Card::compareTo);
    }
}
