public class Card {
    private Suit suit;
    private Rank rank;
    private Deck deck;

    @Override
    public String toString() {
        return suit.toString() + rank.toString();
    }

    public Card(Suit suit, Rank rank,Deck deck) {
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

    // 先比較階級再比較花色
    public boolean compare(Card card) {
        if (this.getRank().greaterThan(card.getRank())){
            return true;
        }else if (this.getRank().equals(card.getRank())){
            return this.getSuit().greaterThan(card.getSuit());
        }
        return false;
    }
}
