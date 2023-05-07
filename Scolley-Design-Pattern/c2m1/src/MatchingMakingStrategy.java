import java.util.Collection;
import java.util.List;

public interface MatchingMakingStrategy {

    Individual findBestMatchIndividual();
    List<Individual> sortMatchingStrategy(Collection<Individual> individuals, Individual individual);

}
