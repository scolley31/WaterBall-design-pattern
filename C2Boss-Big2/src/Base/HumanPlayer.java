package Base;

import CardPatternHandle.CardPatternHandler;
import Strategy.PlayCardStrategy;

public class HumanPlayer extends Player {

    public HumanPlayer(PlayCardStrategy playCardStrategy, CardPatternHandler cardPatternHandler) {
        super(playCardStrategy, cardPatternHandler);
    }
}
