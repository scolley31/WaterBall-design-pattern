package Strategy.AIChoosePlayCardStrategyHandler;

import Base.CardPattern;
import Base.Player;
import Base.Round;
import Strategy.AIPlayCardStrategy;

public class AIPassStrategyHandler extends AIPlayCardStrategy {
    public AIPassStrategyHandler(AIPlayCardStrategy next) {
        super(next);
    }
    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        return true;
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        player.setPass(true);
        return null;
    }
}
