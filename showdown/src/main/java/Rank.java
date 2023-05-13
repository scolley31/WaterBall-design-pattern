public enum Rank {
    THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A"), DEUCE("2");

    private String value;

    Rank(String i) {
        this.value = i;
    }

    public String getValue() {
        return this.value;
    }

    public Rank getNext() {
        return values()[(ordinal() + 1) % values().length];
    }
}