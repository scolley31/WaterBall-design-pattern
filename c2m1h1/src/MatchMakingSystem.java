import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class MatchMakingSystem {
    private Collection<Individual> individuals;
    private MatchStrategy matchStrategy;

    public MatchMakingSystem(Collection<Individual> individuals, MatchStrategy matchStrategy) {
        setIndividuals(individuals);
        setMatchStrategy(matchStrategy);
    }

    public void match(Individual toBeMatched) {
        Individual matched = matchStrategy.match(individuals, toBeMatched);

        System.out.printf("Id=%d的對象已與Id=%d的對象配對成功\n", toBeMatched.getId(), matched.getId());
        System.out.println();
        System.out.println("以下為兩位對象的基本資訊：");
        System.out.printf("----------Id=%d----------\n", toBeMatched.getId());
        System.out.printf("%s", toBeMatched);
        System.out.println();
        System.out.printf("----------Id=%d----------\n", matched.getId());
        System.out.printf("%s", matched);
    }

    public Collection<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Collection<Individual> individuals) {
        this.individuals = requireNonNull(individuals);
    }

    public MatchStrategy getMatchStrategy() {
        return matchStrategy;
    }

    public void setMatchStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = requireNonNull(matchStrategy);
    }


}
