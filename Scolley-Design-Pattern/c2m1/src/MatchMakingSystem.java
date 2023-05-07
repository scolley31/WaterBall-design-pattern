import java.util.Collection;

public class MatchMakingSystem {

    Individual yourSelf;
    Collection<Individual> individuals;
    MatchingMakingStrategy matchingMakingStrategy;

    public MatchMakingSystem(Builder builder) {
        yourSelf = builder.yourSelf;
        individuals = builder.individuals;
        matchingMakingStrategy = builder.matchingMakingStrategy;
    }

    public void match(){
        matchingMakingStrategy.sortMatchingStrategy(individuals, yourSelf);
        yourSelf.addMatchIndividual(matchingMakingStrategy.findBestMatchIndividual());
        matchingMakingStrategy.findBestMatchIndividual().addMatchIndividual(yourSelf);
    }

    public static class Builder {
        private Individual yourSelf;
        private Collection<Individual> individuals;
        private MatchingMakingStrategy matchingMakingStrategy;

        public Builder setYourSelf(Individual yourSelf) {
            this.yourSelf = yourSelf;
            return this;
        }

        public Builder setIndividuals(Collection<Individual> individuals) {
            this.individuals = individuals;
            return this;
        }

        public Builder setMatchingMakingStrategy(MatchingMakingStrategy matchingMakingStrategy) {
            this.matchingMakingStrategy = matchingMakingStrategy;
            return this;
        }

        public MatchMakingSystem build() {
            return new MatchMakingSystem(this);
        }
    }
}
