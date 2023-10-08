public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }


    public enum Rank {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), Jack("J"), Queen("Q"), King("K"), Ace("A");

        final String rank;

        Rank(String rank) {
            this.rank = rank;
        }

        @Override
        public String toString() {
            return rank;
        }
    }

    public enum Suit {
        CLUB("♣"), DIAMOND("♦"), HEART("♥"), SPADE("♠");

        final String suit;

        Suit(String suit) {
            this.suit = suit;
        }

        @Override
        public String toString() {
            return suit;
        }
    }
}
