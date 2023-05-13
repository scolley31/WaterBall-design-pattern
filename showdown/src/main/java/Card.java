public class Card {

    private Rank rank;

    private Suit suit;

    public Rank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int compareTo(Object o) {
        Card c = (Card)o;
        int rankCompare = rank.compareTo(c.rank); //enum compare > 0 : larger, < 0 smaller, = 0 equals
        return rankCompare != 0 ? rankCompare : suit.compareTo(c.suit);
    }

}