public enum Suit {
    CLUBS(1,"♣"),
    DIAMONDS(2,"♦"),
    HEARTS(3,"♥"),
    SPADES(4,"♠");

    private int level;
    private String name;

    Suit(int level,String name) {
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

