import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HabitBasedStrategy implements MatchingMakingStrategy {

    private List<Individual> individuals;

    @Override
    public Individual findBestMatchIndividual() {
        return individuals.get(0);
    }

    @Override
    public List<Individual> sortMatchingStrategy(Collection<Individual> individuals, Individual individual) {
        List<Individual> sortedIndividuals = individuals.stream()
                .sorted(Comparator.comparingInt(individual1 ->
                    individual1.habitBasedMatchCount(individual)
                ))
                .collect(Collectors.toList());
        Collections.reverse(sortedIndividuals);
        this.individuals = sortedIndividuals;

        System.out.println("HabitBasedStrategy");
        this.individuals.forEach(individual1 -> {
            System.out.println("id = " + individual1.id);
        });

        return sortedIndividuals;
    }
}
