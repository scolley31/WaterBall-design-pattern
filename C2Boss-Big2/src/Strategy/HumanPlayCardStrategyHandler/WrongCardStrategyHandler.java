package Strategy.HumanPlayCardStrategyHandler;

import Base.CardPattern;
import Base.Player;
import Base.Round;
import Strategy.HumanPlayCardStrategy;

public class WrongCardStrategyHandler extends HumanPlayCardStrategy {

    public WrongCardStrategyHandler(HumanPlayCardStrategy next) {
        super(next);
    }
    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        return true;
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        if (round.getTopPlay() == null && player.isPass()) {
            player.setPass(false);
        }
        return null;
    }
}
