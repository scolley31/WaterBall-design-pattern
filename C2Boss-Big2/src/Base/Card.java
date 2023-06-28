package Base;

public class Card implements Comparable<Card> {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (suit != null ? suit.hashCode() : 0);
        return result;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int compareTo(Card card) {
        if (this.getRank().getValue() == card.getRank().getValue()) {
            return this.getSuit().getValue() - card.getSuit().getValue();
        }
        return this.getRank().getValue() - card.getRank().getValue();
    }

    public enum Rank {
        THREE(1, "3"),

        FOUR(2, "4"),

        FIVE(3, "5"),

        SIX(4, "6"),

        SEVEN(5, "7"),

        EIGHT(6, "8"),

        NINE(7, "9"),

        TEN(8, "10"),

        JACK(9, "11"),

        QUEEN(10, "12"),

        KING(11, "13"),

        ACE(12, "1"),

        TWO(13, "2");
        private int value;
        private String name;

        Rank(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            if (name.equals("1"))
                return "[A]";
            return "[" + name + "]";
        }
    }

    public enum Suit {
        CLUB(1, "C"),
        DIAMOND(2, "D"),
        HEART(3, "H"),
        SPADE(4, "S");

        private int value;
        private String name;

        Suit(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public String toString() {
        return suit.toString() + rank.toString();
    }
}
