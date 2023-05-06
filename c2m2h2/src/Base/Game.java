package Base;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class Game<Player extends Base.Player<Card>, Card> {
    protected Set<Player> players;
    protected final Deck<Card> deck;
    protected Player turnPlayer;
    protected int turn = 0;
    protected int round = 0;
    protected Map turnInformationMap = new HashMap<Player, Card>();

    protected Game(Set<Player> players, Deck<Card> deck) {
        this.players = players;
        this.deck = deck;
    }

    public void start() {
        playersNamedHimself();
        shuffleTheDeck();
        playersDrawCard();
        playGame();
    }

    protected void playersNamedHimself() {
        for (Base.Player player : players) {
            player.nameHimself();
        }
    }

    protected void shuffleTheDeck() {
        deck.shuffle();
    }

    protected void playersDrawCard() {
        int initialHandSize = initializeHandsSize();
        for (int i = 0; i < initialHandSize; i++) {
            for (Player player : players) {
                Card card = deck.drawCard();
                player.addHandCard(card);
            }
        }
    }

    protected abstract int initializeHandsSize();

    protected void playGame() {
        turnPlayer = players.stream().collect(Collectors.toList()).get(turn % players.size());
        takeTurn(turnPlayer);
        turn++;
        if (turn % players.size() == 0) {
            round++;
            onRoundEnd();
        }
        if (isGameOver()) {
            showWinner();
        } else {
            playGame();
        }

    }

    protected abstract boolean isGameOver();

    protected void onRoundEnd() {

    }

    ;

    protected abstract void showWinner();

    protected abstract void takeTurn(Player turnPlayer);

}
