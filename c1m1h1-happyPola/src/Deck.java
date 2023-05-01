import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = initDeck();

    public void shuffle() {
        Collections.shuffle(this.cards);
    }
    
    public void drawCard(Player player) {
        for (int i = 0; i < 13; i++) {
            player.getHand().addCard(cards.pop());
        }
    }

    private Stack<Card> initDeck() {
        Stack<Card> deck = new Stack();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank,this);
                deck.add(card);
                card.setDeck(this);
            }
        }
        return deck;
    }
}
