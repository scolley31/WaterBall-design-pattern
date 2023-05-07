import java.util.Collection;
import java.util.List;

public class HabitBasedStrategy implements MatchingMakingStrategy{

    private List<Individual> individuals;
    @Override
    public Individual findBestMatchIndividual() {
        return individuals.get(0);
    }

    @Override
    public List<Individual> sortMatchingStrategy(Collection<Individual> individuals, Individual individual) {
        return null;
    }
}
