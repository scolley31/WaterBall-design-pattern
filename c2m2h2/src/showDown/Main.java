package showDown;

import Base.Game;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Player> playerList = initAIPlayers();
        Game game = new ShowDown(Deck.initShowDownDeck(), playerList);
        playerList.forEach(p -> p.setShowDown(game));
        game.start();
    }

    private static Set<Player> initAIPlayers() {
        AIPlayer ai1 = new AIPlayer("AI1");
        AIPlayer ai2 = new AIPlayer("AI2");
        AIPlayer ai3 = new AIPlayer("AI3");
        AIPlayer ai4 = new AIPlayer("AI4");
//        HumanPlayer ai4 = new HumanPlayer("human");
        Set<Player> playerList = Set.of(ai1, ai2, ai3, ai4);
        return playerList;
    }
}


