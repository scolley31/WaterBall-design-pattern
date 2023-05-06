package showDown;

public class Card  {
    private Suit suit;
    private Rank rank;
    private Deck deck;

    public enum Suit {
        CLUBS(1, "♣"),
        DIAMONDS(2, "♦"),
        HEARTS(3, "♥"),
        SPADES(4, "♠");
        private int level;
        private String name;

        Suit(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public boolean greaterThan(Suit suit) {
            return this.level > suit.level;
        }

        public boolean lessThan(Suit suit) {
            return this.level < suit.level;
        }

        public boolean equals(Suit suit) {
            return this.level == suit.level;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Rank {
        TWO(2, "2"),
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        JACK(11, "J"),
        QUEEN(12, "Q"),
        KING(13, "K"),
        ACE(14, "A");
        private int level;
        private String name;

        Rank(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public boolean greaterThan(Rank rank) {
            return this.level > rank.level;
        }

        public boolean lessThan(Rank rank) {
            return this.level < rank.level;
        }

        public boolean equals(Rank rank) {
            return this.level == rank.level;
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

    public Card(Suit suit, Rank rank, Deck deck) {
        this.suit = suit;
        this.rank = rank;
        this.deck = deck;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public boolean compare(Card card) {
        if (this.getRank().greaterThan(card.getRank())){
            return true;
        }else if (this.getRank().equals(card.getRank())){
            return this.getSuit().greaterThan(card.getSuit());
        }
        return false;
    }
}

