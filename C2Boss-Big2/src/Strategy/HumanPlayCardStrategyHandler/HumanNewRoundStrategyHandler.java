package Strategy.HumanPlayCardStrategyHandler;

import Base.CardPattern;
import Base.Player;
import Base.Round;
import Strategy.HumanPlayCardStrategy;

public class HumanNewRoundStrategyHandler extends HumanPlayCardStrategy {
    public HumanNewRoundStrategyHandler(HumanPlayCardStrategy next) {
        super(next);
    }

    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        return round.getTopPlay() == null;
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        return cardPattern;
    }
}
