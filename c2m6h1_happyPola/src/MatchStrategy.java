import java.util.List;

public interface MatchStrategy {
    List<Individual> match(Individual matchingIndividual);
}
