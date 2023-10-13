import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class DifferentSelectionStrategy implements SelectionStrategy{
    @Override
    public Individual select(Collection<Individual> individuals, Comparator<Individual> comparator) {
        Optional<Individual> toBeMatched = individuals.stream().max(comparator);

        if(toBeMatched.isPresent()){
            return toBeMatched.get();
        }
        else{
            throw new RuntimeException("No suitable matches found for the individual.");
        }
    }
}
