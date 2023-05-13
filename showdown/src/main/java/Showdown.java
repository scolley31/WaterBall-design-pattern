import java.util.*;

public class Showdown {
    public static final int PLAYER_SIZE = 4;
    public static final int TURNS = 13;
    private List<Player> players = new ArrayList<>(PLAYER_SIZE);
    private Deck deck;
    private Player winner;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    Showdown() {
        Deck deck = new Deck();
        deck.shuffle();
        setDeck(deck);
    }

    public void start() {
        initPlayers();
        drawing();
        sorting();
        for (int i = 0; i < TURNS; i++)
            takeTurn();
        showWinner();
    }

    private void sorting() {
        for (Player p : players) {
            p.getHands().sort(Card::compareTo);
        }
    }

    private void showWinner() {
        List<Integer> scores = new ArrayList<>();
        HashMap<Integer, Player> playerScoreMap = new HashMap<>();
        for (Player p : players) {
            playerScoreMap.put(p.getScore(), p);
            scores.add(p.getScore());
        }
        Collections.sort(scores);
        setWinner(playerScoreMap.get(scores.get(3)));
        System.out.println("winner is :" + winner.getName());
    }

    private void takeTurn() {
        Turn turn = new Turn();
        turn.start(players);
    }

    void drawing() {
        for (int i = Deck.FULL_DECK_SIZE; i > 0; i--) {
            Player player = players.get(i % PLAYER_SIZE);
            Card card = deck.drawCard();
            player.addHandCard(card);
        }
    }

    void initPlayers() {
        List<Player> temps = new ArrayList<>(PLAYER_SIZE);
        Player player = new HumanPlayer(nameHimself());
        temps.add(player);
        for (int i = 1; i < 4 ; i++) {
            Player temp = new AIPlayer("AI_" + i);
            temps.add(temp);
        }
        setPlayers(temps);
    }

    private String nameHimself() {
        System.out.print("print your name: ");
        Scanner s = new Scanner(System.in);
        return s.next();
    }

    public List<Player> getAllPlayer() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
