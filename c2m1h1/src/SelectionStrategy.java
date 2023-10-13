import java.util.Collection;
import java.util.Comparator;

public interface SelectionStrategy {
    Individual select(Collection<Individual> individuals, Comparator<Individual> comparator);
}
