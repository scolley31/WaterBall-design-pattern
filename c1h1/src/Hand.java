import static java.util.Objects.requireNonNull;

public class Hand extends Card implements Comparable<Hand> {
    private Player player;

    public Hand(Rank rank, Suit suit) {
        super(rank, suit);
    }

    public String toString() {
        return getSuit().toString() + getRank().toString();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = requireNonNull(player);
    }

    @Override
    public int compareTo(Hand hand) {
        if (this.getRank().ordinal() > hand.getRank().ordinal()) {
            return 1;
        } else if (this.getRank().ordinal() < hand.getRank().ordinal()) {
            return -1;
        }

        if (this.getSuit().ordinal() > hand.getSuit().ordinal()) {
            return 1;
        } else if (this.getSuit().ordinal() < hand.getSuit().ordinal()) {
            return -1;
        }

        return 0;
    }
}
