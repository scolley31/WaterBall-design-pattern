import java.util.Comparator;

public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        setRank(rank);
        setSuit(suit);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public static Card bigThan(Card c1, Card c2) {

        if (c1 == null && c2 == null) throw new IllegalArgumentException("沒有牌可以比大小") ;
        if (c1 == null) return c2;
        if (c2 == null) return c1;

        int compareRank = c1.getRank().getRank() - c2.getRank().getRank();
        int compareSuit = c1.getSuit().getSuit() - c2.getSuit().getSuit();
        if (compareRank > 0) {
            return c1;
        } else if (compareRank < 0) {
            return c2;
        }
        if (compareSuit > 0) {
            return c1;
        } else if (compareSuit < 0) {
            return c2;
        }
        return null;
    }

}
