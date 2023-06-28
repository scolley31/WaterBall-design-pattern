package showDown;

import Base.Game;

import java.util.*;

public class ShowDown extends Game<Player, Card> {
    private int maxRound = 13;
    private Deck deck;
    private final Set<Player> players;
    private int currentRound = 0;

    public ShowDown(Deck deck, Set<Player> player) {
        super(player, deck);
        this.deck = deck;
        this.players = player;
    }

    protected void shuffleTheDeck() {
        deck.shuffle();
    }

    protected void showWinner() {
        this.players.stream().forEach(player -> {
            System.out.println(player.getName() + "score is : " + player.getPoint());
        });
        this.players.stream()
                .max((p1, p2) -> p1.getPoint() - p2.getPoint())
                .ifPresent(player -> {
                    System.out.println(player.getName() + " is the winner!");
                });
    }

    @Override
    protected void takeTurn(Player turnPlayer) {
        Card card = turnPlayer.takeTurn();
        turnInformationMap.put(turnPlayer, card);
    }

    @Override
    protected int initializeHandsSize() {
        return 13;
    }

    @Override
    protected boolean isGameOver() {
        return round >= maxRound;
    }

    @Override
    protected void onRoundEnd() {
        showTurnCards(turnInformationMap);
        winnerGainPoint(turnInformationMap);
        turnInformationMap.clear();
    }
    private void winnerGainPoint(Map<Player, Card> informationMap) {
        informationMap.values().stream()
                .max((c1, c2) -> c1.compare(c2) ? 1 : -1)
                .ifPresent(card -> {
                    for (Map.Entry<Player, Card> entry : informationMap.entrySet()) {
                        if (entry.getValue().equals(card)) {
                            entry.getKey().gainPoint();
                            System.out.println(entry.getKey().getName() + " wins this round!");
                        }
                    }
                });
    }

    private void showTurnCards(Map<Player, Card> turnCards) {
        for (Map.Entry<Player, Card> entry : turnCards.entrySet()) {
            System.out.println(entry.getKey().getName() + " plays " + entry.getValue().toString());
        }
    }
}