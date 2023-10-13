import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class HabitBasedStrategy extends MatchStrategy{
    private SelectionStrategy selectionStrategy = new SimilarSelectionStrategy();

    public void setSelectionStrategy(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    @Override
    public Individual match(Collection<Individual> individuals, Individual toBeMatched) {
        Comparator<Individual> comparator = Comparator.<Individual, Long>comparing(matched-> {
            ArrayList<String> matchedHabits = matched.getHabits();
            ArrayList<String> toBeMatchedHabits = toBeMatched.getHabits();

            return matchedHabits.stream().filter(toBeMatchedHabits::contains).count();
        }, Comparator.reverseOrder()).thenComparingInt(Individual::getId);

        Collection<Individual> targets = individuals.stream().filter(target -> target!= toBeMatched).toList();

        return selectionStrategy.select(targets, comparator);
    }
}
