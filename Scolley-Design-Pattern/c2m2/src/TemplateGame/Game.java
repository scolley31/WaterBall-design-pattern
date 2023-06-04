package TemplateGame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

public abstract class Game<Card> {

    protected AbstractPlayer<Card> winner;

    protected Deck<Card> deck;

    protected int turn = 0;

    private int originHandCardNumber;

    protected Stack<Card> alreadyShowCardStack = new Stack<>();

    protected final Collection<AbstractPlayer<Card>> players = new HashSet<>();

    public Game(AbstractPlayer<Card> player1, AbstractPlayer<Card> player2, AbstractPlayer<Card> player3, AbstractPlayer<Card> player4, int originHandCardNumber) {
        players.add(player1);
        player1.setGame(this);
        players.add(player2);
        player2.setGame(this);
        players.add(player3);
        player3.setGame(this);
        players.add(player4);
        player4.setGame(this);
        setHandCardNumber(originHandCardNumber);
    }

    public void start() {

        players.forEach(this::playersNameThemSelves);

        addCardsToDeck();

        deckShuffle();

        drawCardUntilHandsCardEqualSpecifiedNumbers();

        needToDoBeforeGame();

        while (isGameOver()) {
            players.forEach(this::takeTurn);
            turn++;
            turnEnd();
        }

        showWinner();
    }

    protected abstract void turnEnd();

    private void playersNameThemSelves(AbstractPlayer<Card> player) {
        player.nameHimself();
    }

    protected abstract void addCardsToDeck();

    protected void deckShuffle() {
        deck.shuffle();
    }

    protected void setHandCardNumber(int cardNumber) {
        originHandCardNumber = cardNumber;
    }

    protected void drawCardUntilHandsCardEqualSpecifiedNumbers() {
        players.forEach(player -> {
            while (player.getHands().size() < originHandCardNumber) {
                Card card = deck.drawCard();
                player.addHandCard(card);
            }
        });
    }

    protected abstract void needToDoBeforeGame();

    protected abstract void takeTurn(AbstractPlayer<Card> player);

    protected abstract boolean isGameOver();

    private void showWinner() {
        System.out.println("Winner's name " + winner.getName());
    }

}
