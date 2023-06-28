package Strategy.AIChoosePlayCardStrategyHandler;

import Base.Card;
import Base.CardPattern;
import Base.Player;
import Base.Round;
import CardPatternHandle.cardPatternHandler.FullHouseHandler;
import Strategy.AIPlayCardStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AIFullHouseStrategyHandler extends AIPlayCardStrategy {
    private FullHouseHandler fullHouseHandler = new FullHouseHandler(null);


    public AIFullHouseStrategyHandler(AIPlayCardStrategy next) {
        super(next);
    }

    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        CardPattern convertCardPattern = fullHouseHandler.convertCardPattern(getFullHouseValidCards(player));
        if (convertCardPattern == null){
            return false;
        }
        return convertCardPattern.isBiggerThan(round.getTopPlay());
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        List<Card> validCards = getFullHouseValidCards(player);
        player.getHandCards().removeAll(validCards);
        return fullHouseHandler.convertCardPattern(validCards);
    }

    private Map<Card.Rank, Long> countRankOccurrences(Player player) {
        return player.getHandCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }

    private List<Card> getFullHouseValidCards(Player player) {
        Map<Card.Rank, Long> rankOccurrences = countRankOccurrences(player);
        return player.getHandCards().stream()
                .filter(card -> {
                    Card.Rank rank = card.getRank();
                    long occurrences = rankOccurrences.getOrDefault(rank, 0L);
                    return occurrences >= 3 || occurrences == 2;
                })
                .limit(5)
                .collect(Collectors.toList());
    }
}
