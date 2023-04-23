import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cardList = new ArrayList<>();

    public Deck() {
        initCardList();
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void initCardList() {
        for (Rank rank: Rank.values()) {
            for (Suit suit: Suit.values()) {
                Card card = new Card(rank, suit);
                cardList.add(card);
            }
        }
        System.out.println("Deck already init");
        cardList.forEach( card ->
                System.out.println("Card : Rank = "+card.getRank()+" Suit = "+card.getSuit())
        );
    }

    public void shuffle() {
        Collections.shuffle(cardList);
        System.out.println("Deck already shuffle");
        cardList.forEach( card ->
                System.out.println("Card : Rank = "+card.getRank()+" Suit = "+card.getSuit())
        );
    }

    public Card drawCard() {
        Card card = cardList.stream()
                .findFirst()
                .orElse(null);
        cardList.remove(card);
        return card;
    }

}
