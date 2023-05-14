import java.util.ArrayList;
import java.util.List;

public class MatchmakingSystem {
    private List<Individual> individuals;
    private MatchStrategy matchStrategy;

    public MatchmakingSystem(List<Individual> individuals, MatchStrategy matchStrategy) {
        for (Individual individual : individuals) {
            individual.setMatchmakingSystem(this);
        }
        this.individuals = individuals;
        this.matchStrategy = matchStrategy;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public Individual match(Individual matchingIndividual){
        return matchStrategy.match(matchingIndividual).get(0);
    }

    public void setMatchStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }
}
