package uno;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Player> players = initAIPlayers();
        Uno game = new Uno(Deck.initUnoDeck(), players);
        players.forEach(p -> p.setUno(game));
        game.start();
    }

    private static Set<Player> initAIPlayers() {
        AIPlayer ai1 = new AIPlayer("AI1");
        AIPlayer ai2 = new AIPlayer("AI2");
        AIPlayer ai3 = new AIPlayer("AI3");
        AIPlayer ai4 = new AIPlayer("AI4");
//        HumanPlayer ai4 = new HumanPlayer("human");
        Set<Player> players = Set.of(ai1, ai2, ai3, ai4);
        return players;
    }
}
