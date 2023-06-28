package Strategy.AIChoosePlayCardStrategyHandler;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.Pair;
import Base.Player;
import Base.Round;
import CardPatternHandle.cardPatternHandler.PairHandler;
import Strategy.AIPlayCardStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AIPairCardStrategyHandler extends AIPlayCardStrategy {
    private PairHandler pairHandler = new PairHandler(null);

    public AIPairCardStrategyHandler(AIPlayCardStrategy next) {
        super(next);
    }

    @Override
    protected boolean match(Round round, Player player, CardPattern cardPattern) {
        List<Card> pairCards = new ArrayList<>();
        addPairCards(round, player, pairCards);
        for (int i = 0; i < pairCards.size(); i += 2) {
            if (pairHandler.convertCardPattern(Arrays.asList(pairCards.get(i), pairCards.get(i + 1))).isBiggerThan(round.getTopPlay())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected CardPattern doHandler(Round round, Player player, CardPattern cardPattern) {
        List<Card> pairCards = new ArrayList<>();
        addPairCards(round, player, pairCards);
        List<Card> biggerPairCards = new ArrayList<>();

        for (int i = 0; i < pairCards.size(); i += 2) {
            if (new Pair(Arrays.asList(pairCards.get(i), pairCards.get(i + 1))).isBiggerThan(round.getTopPlay())) {
                biggerPairCards.add(pairCards.get(i));
                biggerPairCards.add(pairCards.get(i + 1));
            }
        }
        if (biggerPairCards.size() > 2) {
            biggerPairCards = biggerPairCards.subList(0, 2);
        }
//        List<Card> biggerPairCards = IntStream.range(0, pairCards.size() - 1)
//                .filter(i -> pairHandler.getCardPattern(Arrays.asList(pairCards.get(i), pairCards.get(i + 1))).isBiggerThan(round.getTopPlay()))
//                .mapToObj(i -> Arrays.asList(pairCards.get(i), pairCards.get(i + 1)))
//                .flatMap()
//                .limit(2)
//                .collect(Collectors.toList());

        player.getHandCards().removeAll(biggerPairCards);
        return pairHandler.convertCardPattern(biggerPairCards);
    }

    private static void addPairCards(Round round, Player player, List<Card> filterCards) {
        if (round.getTopPlay().getCards().size() == 2) {
            for (int i = 0; i < player.getHandCards().size(); i++) {
                for (int j = i + 1; j < player.getHandCards().size(); j++) {
                    if (player.getHandCards().get(i).getRank().getValue() == player.getHandCards().get(j).getRank().getValue()) {
                        filterCards.add(player.getHandCards().get(i));
                        filterCards.add(player.getHandCards().get(j));
                    }
                }
            }
        }
    }
}
