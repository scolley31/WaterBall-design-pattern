import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class distanceBasedStrategy implements MatchStrategy {

    @Override
    public List<Individual> match(Individual matchingIndividual) {
        List<Individual> individuals = matchingIndividual.getMatchmakingSystem().getIndividuals();
        Comparator<Individual> comparing = Comparator.comparingInt(individual -> individual.getDistance(matchingIndividual));
       return individuals.stream()
                .filter(individual -> !individual.getGender().equals(matchingIndividual.getGender()))
                .sorted(comparing)
                .collect(Collectors.toList());
    }
}
