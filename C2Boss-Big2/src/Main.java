import Base.*;
import CardPatternHandle.cardPatternHandler.FullHouseHandler;
import CardPatternHandle.cardPatternHandler.PairHandler;
import CardPatternHandle.cardPatternHandler.SingleHandler;
import CardPatternHandle.cardPatternHandler.StraightHandler;
import Strategy.AIChoosePlayCardStrategyHandler.*;
import Strategy.HumanPlayCardStrategyHandler.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Player player1 = new AIPlayer(new AINewRoundStrategyHandler(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player player2 = new AIPlayer(new AINewRoundStrategyHandler(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player player3= new AIPlayer(new AINewRoundStrategyHandler(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player player4 = new HumanPlayer(new HumanNewRoundStrategyHandler(new HumanSingleCardStrategyHandler(new HumanPairCardStrategyHandler(new HumanStraightStrategyHandler(new HumanFullHouseCardStrategyHandler(new WrongCardStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Big2 big2 = new Big2(new Deck(), Arrays.asList(player1, player2, player3, player4));
        big2.start();
    }
}