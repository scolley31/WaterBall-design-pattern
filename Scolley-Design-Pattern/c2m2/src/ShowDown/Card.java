package ShowDown;

public record Card(Rank rank, Suit suit) {

    public static Card bigThan(Card c1, Card c2) {

        if (c1 == null && c2 == null) throw new IllegalArgumentException("沒有牌可以比大小") ;
        if (c1 == null) return c2;
        if (c2 == null) return c1;

        int compareRank = c1.rank().getRank() - c2.rank().getRank();
        int compareSuit = c1.suit().getSuit() - c2.suit().getSuit();
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
