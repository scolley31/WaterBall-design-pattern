import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public static final int FULL_DECK_SIZE = 52;
    List<Card> cards = new ArrayList<>(FULL_DECK_SIZE);
    private boolean isShuffled = false;

    Deck() {
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                cards.add(new Card(rank, suit));
    }

    public boolean isShuffled() {
        return isShuffled;
    }

    public void setShuffled(boolean shuffled) {
        isShuffled = shuffled;
    }

    public void shuffle() {
        Collections.shuffle(cards);

        // show deck
        System.out.print("<Shuffled Deck> := ");
        for (Card card : cards) {
            System.out.print("<" +  card.suit() + ">" + "[<" + card.rank().getValue() + ">]  ");
        }
        System.out.println();
        this.setShuffled(true);
    }

    public Card drawCard() {
        Card c = null;
        if (this.cards.size() > 0) {
            c = cards.get(cards.size() -1);
            cards.remove(c);
        }
        return c;
    }
}
