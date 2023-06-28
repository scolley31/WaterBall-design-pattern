import java.util.List;

import static java.util.Collections.reverse;

public class RevereStrategy implements MatchStrategy {
    private final MatchStrategy matchStrategy;

    public RevereStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    public static RevereStrategy reversed(MatchStrategy matchStrategy) {
        return new RevereStrategy(matchStrategy);
    }

    @Override
    public List<Individual> match(Individual matchingIndividual) {
        List<Individual> match = matchStrategy.match(matchingIndividual);
        reverse(match);
        return match;
    }
}
