import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HabitBasedStrategy implements MatchingMakingStrategy {

    private List<Individual> individuals;

    @Override
    public Individual findBestMatchIndividual() {
        return individuals.get(0);
    }

    @Override
    public List<Individual> sortMatchingStrategy(Collection<Individual> individuals, Individual individual) {

        Comparator<Individual> comparing = Comparator.comparingInt(individual1 ->
                habitBasedMatchCount(individual, individual1)
        );

        List<Individual> sortedIndividuals = individuals.stream()
                .sorted(comparing.reversed().thenComparing(Individual::getId))
                .collect(Collectors.toList());

        this.individuals = sortedIndividuals;

        System.out.println("HabitBasedStrategy");
        this.individuals.forEach(individual1 -> {
            System.out.println("id = " + individual1.getId());
        });

        return sortedIndividuals;
    }

    private int habitBasedMatchCount(Individual individual1, Individual individual2) {

        Collection<String> habit1 = new ArrayList<>(Arrays.stream(individual1.getHabit()).toList());
        Collection<String> habit2 = List.of(individual2.getHabit());
        habit1.retainAll(habit2);
        System.out.println("id = "+individual1.getId()+" 與 id = "+individual2.getId()+ "有 "+habit1.size()+"種興趣match");

        return habit1.size();
    }

}
