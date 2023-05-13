import java.util.*;


public class ReverseStrategy implements MatchingMakingStrategy{

    MatchingMakingStrategy matchingMakingStrategy;
    private List<Individual> individuals;

    public ReverseStrategy(MatchingMakingStrategy matchingMakingStrategy) {
        this.matchingMakingStrategy = matchingMakingStrategy;
    }

    @Override
    public Individual findBestMatchIndividual() {
        return individuals.get(0);
    }

    @Override
    public List<Individual> sortMatchingStrategy(Collection<Individual> individuals, Individual individual) {
        List<Individual> sortedIndividuals = matchingMakingStrategy.sortMatchingStrategy(individuals, individual);
        Collections.reverse(sortedIndividuals);
        this.individuals = sortedIndividuals;

        System.out.println("ReverseStrategy");
        this.individuals.forEach(individual1 -> {
            System.out.println("id = " + individual1.id);
        });

        return sortedIndividuals;
    }


}
