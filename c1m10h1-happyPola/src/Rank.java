public enum Rank {
    TWO(2,"2"),
    THREE(3,"3"),
    FOUR(4,"4"),
    FIVE(5,"5"),
    SIX(6,"6"),
    SEVEN(7,"7"),
    EIGHT(8,"8"),
    NINE(9,"9"),
    TEN(10,"10"),
    JACK(11,"J"),
    QUEEN(12,"Q"),
    KING(13,"K"),
    ACE(14,"A");
    private int level;
    private String name;

    Rank(int level,String name) {
        this.level = level;
        this.name = name;
    }
    public boolean greaterThan(Rank rank) {
        return this.level > rank.level;
    }

    public boolean equals(Rank rank) {
        return this.level == rank.level;
    }

    @Override
    public String toString() {
        return name;
    }
}
