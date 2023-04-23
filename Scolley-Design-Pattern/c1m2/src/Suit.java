public enum Suit {

    CLUB(1),
    DIAMOND(2),
    HEART(3),
    SPADE(4);

    private int suit;
    Suit(int suit) {
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }
}
