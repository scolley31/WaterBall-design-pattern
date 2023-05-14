import java.util.ArrayList;
import java.util.List;

public class Hand {
    private Player player;
    private List<Card> cards = new ArrayList<>(13);
    public Hand(Player player) {
        this.player = player;
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    public void removeCard(Card card) {
        cards.remove(card);
    }
    public List<Card> getCards() {
        return cards;
    }
}
