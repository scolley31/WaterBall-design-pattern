package Base;


import CardPatternHandle.CardPatternHandler;
import Strategy.PlayCardStrategy;

public class AIPlayer extends Player {

    public AIPlayer(PlayCardStrategy playCardStrategy, CardPatternHandler cardPatternHandler) {
        super(playCardStrategy, cardPatternHandler);
    }
}
