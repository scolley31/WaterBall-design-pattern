package Strategy.AIChoosePlayCardStrategyHandler;

import Base.Card;
import Base.CardPattern;
import Base.Player;
import Base.Round;
import CardPatternHandle.cardPatternHandler.SingleHandler;
import Strategy.AIPlayCardStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AINewRoundStrategyHandler extends AIPlayCardStrategy {
    private SingleHandler singleHandler = new SingleHandler(null);


    public AINewRoundStrategyHandler(AIPlayCardStrategy next) {
        super(next);
    }

    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        return round.getTopPlay() == null;
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        List<Card> playedCard;
        if (round.isFirstRound()) {
            playedCard = player.getHandCards().stream().filter(card -> card.equals(new Card(Card.Rank.THREE, Card.Suit.CLUB)))
                    .collect(Collectors.toList());
            player.getHandCards().removeAll(playedCard);
        } else {
            int index = (int) (Math.random() * player.getHandCards().size());
            playedCard = Arrays.asList(player.getHandCards().get(index));
            player.getHandCards().removeAll(playedCard);
        }
        return singleHandler.convertCardPattern(playedCard);
    }
}
