package UNO;

import TemplateGame.AbstractPlayer;
import TemplateGame.Game;

import java.util.Stack;

public class UNO extends Game<Card> {

    private Card cardOnTable;

    public UNO(Player player1, Player player2, Player player3, Player player4, int originHandCardNumber) {
        super(player1, player2, player3, player4, originHandCardNumber);
    }

    @Override
    protected void turnEnd() {

    }

    @Override
    protected void addCardsToDeck() {
        for (Color color : Color.values()) {
            for (Number number : Number.values()) {
                Card card = new Card(color, number);
                deck.addCard(card);
            }
        }
    }

    public Card getCardOnTable() {
        return cardOnTable;
    }

    @Override
    protected void needToDoBeforeGame() {
        cardOnTable = deck.drawCard();
    }

    @Override
    protected void takeTurn(AbstractPlayer<Card> player) {
        if (canShowCard(player)) {
            alreadyShowCardStack.add(cardOnTable);
            cardOnTable = player.showCard();
        } else {
            if (deck.cardStackNumber() != 0) {
                player.addHandCard(deck.drawCard());
            } else {
                deck.addCard(alreadyShowCardStack);
                deck.shuffle();
            }
        }
    }

    @Override
    protected boolean isGameOver() {
        winner = players.stream()
                .filter(player -> player.getHands().size() == 0)
                .findFirst()
                .orElse(null);
        return players.stream().anyMatch(player -> player.getHands().size() == 0);
    }

    private boolean canShowCard(AbstractPlayer<Card> player) {
        for (Card card: player.getHands().getHandCards()) {
            if (Card.isTheSameType(card, cardOnTable)) return true;
        }
        return false;
    }
}
