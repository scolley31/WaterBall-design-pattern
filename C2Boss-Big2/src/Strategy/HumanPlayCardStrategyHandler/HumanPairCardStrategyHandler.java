package Strategy.HumanPlayCardStrategyHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Pair;
import Base.Player;
import Base.Round;
import Strategy.HumanPlayCardStrategy;

import java.util.Arrays;

public class HumanPairCardStrategyHandler extends HumanPlayCardStrategy {

    public HumanPairCardStrategyHandler(HumanPlayCardStrategy next) {
        super(next);
    }
    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        return cardPattern.isBiggerThan(round.getTopPlay());
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        return cardPattern;
    }
}
