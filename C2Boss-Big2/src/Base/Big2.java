package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Big2 {
    private final Deck deck;
    private final List<Player> players;
    private final List<Round> rounds = new ArrayList<>();

    public Big2(Deck deck, List<Player> players) {
        this.deck = deck;
        this.players = players;
        Round round = new Round();
        round.setFirstRound(true);
        rounds.add(round);
    }

    public void start() {
        playersNameHimself();
        shuffleTheDeck();
        Deal();
        playGame();
    }

    private void playGame() {
        if (isFirstRound()) {
            firstRoundStart();
        } else
            takeTurn();

        if (isGameOver()) {
            showWinner();
        } else {
            playGame();
        }
    }

    private boolean isFirstRound() {
        return rounds.get(rounds.size() - 1).isFirstRound();
    }

    private void firstRoundStart() {
        Player firstPlayer = findPlayerWhoHasClubThree();
        Round round = rounds.get(rounds.size() - 1);
        round.setTurn(players.indexOf(firstPlayer));
        firstPlayer.play(round);
    }

    private void takeTurn() {
        Round round = rounds.get(rounds.size() - 1);
        Player player = players.get(round.getTurn() % 4);
        if (player.isPass()) {
            jumpThisPlayer(round);
        } else
            player.play(round);

        if (round.isThreePlayerPass(players)) {
            startNewRound();
        }
    }

    private void jumpThisPlayer(Round round) {
        round.setTurn(round.getTurn() + 1);
        rounds.add(round);
        takeTurn();
    }

    private void showWinner() {
        Player winner = players.stream()
                .filter(player -> player.getHandCards().isEmpty())
                .findFirst().orElseThrow(RuntimeException::new);
        System.out.printf("遊戲結束，遊戲的勝利者為 %s", winner.getName());
    }

    private boolean isGameOver() {
        return players.stream()
                .anyMatch(player -> player.getHandCards().isEmpty());
    }

    private void resetPlayersStatus() {
        players.forEach(player -> player.setPass(false));
    }

    private void startNewRound() {
        RoundEnd();
        resetPlayersStatus();
    }

    private void Deal() {
        while (!deck.isEmpty()) {
            for (Player player : players) {
                deck.deal(player);
            }
        }
    }
    private void shuffleTheDeck() {
        deck.shuffle();
    }

    private void playersNameHimself() {
        for (Player player : players) {
            player.nameHimself();
        }
    }

    public Player findPlayerWhoHasClubThree() {
        return players.stream()
                .filter(player -> player.getHandCards().contains(new Card(Card.Rank.THREE, Card.Suit.CLUB)))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
    private void RoundEnd() {
        Round oldRound = rounds.get(rounds.size() - 1);
        Round round = new Round();
        round.setTopPlayer(oldRound.getTopPlayer());
        round.setTurn(players.indexOf(oldRound.getTopPlayer()));
        rounds.add(round);
    }
}
