import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Player> players = initAIPlayers();
        ShowDown game = new ShowDown(new Deck(), players);
        game.start();
    }

    private static Set<Player> initAIPlayers() {
        AIPlayer ai1 = new AIPlayer("AI Player 1");
        AIPlayer ai2 = new AIPlayer("AI Player 2");
        AIPlayer ai3 = new AIPlayer("AI Player 3");
//        AIPlayer ai4 = new AIPlayer("AI Player 4");
        HumanPlayer ai4 = new HumanPlayer("human");
        Set<Player> players = Set.of(ai1, ai2, ai3, ai4);
        return players;
    }
}


