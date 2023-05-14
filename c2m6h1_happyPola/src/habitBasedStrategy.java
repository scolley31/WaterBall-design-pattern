import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class habitBasedStrategy implements MatchStrategy {
    @Override
    public List<Individual> match(Individual matchingIndividual) {
        List<Individual> individualList = matchingIndividual.getMatchmakingSystem().getIndividuals();
        Comparator<Individual> comparing = Comparator.comparingInt(individual -> countCommonInterests(matchingIndividual, individual));
        return individualList.stream()
                .filter(individual -> !individual.getGender().equals(matchingIndividual.getGender()))
                .sorted(comparing.thenComparing(Individual::getId).reversed())
                .collect(Collectors.toList());
    }

    private int countCommonInterests(Individual individual1, Individual individual2) {
        Set<String> interests1 = new HashSet<>(individual1.getInterests());
        Set<String> interests2 = new HashSet<>(individual2.getInterests());
        interests1.retainAll(interests2);
        return interests1.size();
    }
}
