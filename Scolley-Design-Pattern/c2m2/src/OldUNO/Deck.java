package OldUNO;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cardList = new ArrayList<>();
    public Deck() {
        initCardList();
    }

    private void initCardList() {
        for (Color color: Color.values()) {
            for (Number number: Number.values()) {
                Card card = new Card(color, number);
                cardList.add(card);
            }
        }
        System.out.println("OldUNO.Deck already init");
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void addAlreadyShowCardAndShuffle(ArrayList<Card> cardList) {
        this.cardList.addAll(cardList);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cardList);
        System.out.println("OldUNO.Deck already shuffle");
    }

    public Card drawCard() {
        Card card = cardList.stream()
                .findFirst()
                .orElse(null);
        cardList.remove(card);
        return card;
    }
}
