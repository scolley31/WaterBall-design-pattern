package ShowDown;

import TemplateGame.AbstractPlayer;
import TemplateGame.Deck;
import TemplateGame.Game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ShowDown extends Game<Card> {

    private Card maxShowCard = null;

    private final Map<Player, Card> playCardThisTurn = new HashMap<>();

    private static final int TURN_NUMBER = 13;


    public ShowDown(Player player1, Player player2, Player player3, Player player4, int originHandCardNumber) {
        super(player1, player2, player3, player4, originHandCardNumber);
    }

    @Override
    protected void addCardsToDeck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                deck.addCard(card);
            }
        }
        deck.getCardStack().forEach( card ->
                System.out.println("Card = "+card)
        );
        System.out.println("ShowDown.Deck already init");
    }

    @Override
    protected void takeTurn(AbstractPlayer<Card> player) {
        Player showDownPlayer = (Player) player;
        playCardThisTurn.put(showDownPlayer, player.showCard());
    }

    @Override
    protected void turnEnd() {
        for (AbstractPlayer<Card> player : players) {
            maxShowCard = Card.bigThan(maxShowCard, ((Player) player).getShowCard());
        }

        Player getPointThisTurnPlayer = getWinnerThisTurn(playCardThisTurn, maxShowCard);
        if (getPointThisTurnPlayer != null) {
            getPointThisTurnPlayer.gainPoint();
        }
    }

    private Player getWinnerThisTurn(Map<Player, Card> playCardThisTurn, Card maxShowCard) {
        for (Map.Entry<Player, Card> entry : playCardThisTurn.entrySet()) {
            if (entry.getValue().equals(maxShowCard)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean isGameOver() {
        return turn == TURN_NUMBER;
    }

    @Override
    protected void showWinner() {
        winner = players.stream()
                .max(Comparator.comparingInt(player -> ((Player) player).getPoint()))
                .orElse(null);
        assert winner != null;
        System.out.println("Winner's name " + winner.getName());
    }

}
