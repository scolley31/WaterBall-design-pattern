package Strategy;

import Base.*;

public abstract class PlayCardStrategy {

    private PlayCardStrategy next;

    public PlayCardStrategy(PlayCardStrategy next) {
        this.next = next;
    }

    public CardPattern play(Round round, Player player, CardPattern cardPattern) {
        if (match(round, player, cardPattern)) {
            return doHandler(round, player, cardPattern);
        } else if (next != null) {
            return next.play(round, player, cardPattern);
        }
        return null;
    }

    protected abstract boolean match(Round round, Player player, CardPattern cardPattern);

    protected abstract CardPattern doHandler(Round round, Player player, CardPattern cardPattern);

}
