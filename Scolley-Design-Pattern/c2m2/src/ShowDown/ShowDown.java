package ShowDown;

import TemplateGame.AbstractPlayer;
import TemplateGame.Game;

import java.util.HashMap;

public class ShowDown extends Game<Card> {

    private Card maxShowCard = null;

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
    }

    @Override
    protected void needToDoBeforeGame() {
        // TODO
    }

    @Override
    protected void takeTurn(AbstractPlayer<Card> player) {
        player.showCard();
    }

    @Override
    protected boolean isGameOver() {
        return turn == TURN_NUMBER;
    }
}
