import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        // 初始化牌堆，每種花色的每種階級各一張
        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                this.cards.add(new Card(rank, suit));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        try {
            System.out.print("【系統】正在為牌堆進行洗牌中，請稍後.");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.println(".");
            Thread.sleep(1000);
            Collections.shuffle(cards);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Hand getHand() {
        Card card = cards.removeFirst();
        return new Hand(card.getRank(), card.getSuit());
    }
}
