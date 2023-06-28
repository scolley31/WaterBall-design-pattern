package Strategy.AIChoosePlayCardStrategyHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Single;
import Base.Player;
import Base.Round;
import CardPatternHandle.cardPatternHandler.SingleHandler;
import Strategy.AIPlayCardStrategy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AISingleCardStrategyHandler extends AIPlayCardStrategy {
    private SingleHandler singleHandler = new SingleHandler(null);


    public AISingleCardStrategyHandler(AIPlayCardStrategy next) {
        super(next);
    }

    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        return player.getHandCards().stream()
                .anyMatch(card -> singleHandler.convertCardPattern(Arrays.asList(card)).isBiggerThan(round.getTopPlay()));
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        List<Card> validCards = player.getHandCards().stream()
                .filter(card -> new Single(Arrays.asList(card)).isBiggerThan(round.getTopPlay()))
                .collect(Collectors.toList());
        List<Card> playedCard = Arrays.asList(validCards.get(0));
        player.getHandCards().removeAll(playedCard);
        return singleHandler.convertCardPattern(playedCard);
    }
}
