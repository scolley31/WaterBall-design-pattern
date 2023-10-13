import java.util.Collection;

public abstract class MatchStrategy {
    private SelectionStrategy selectionStrategy;
    abstract void setSelectionStrategy(SelectionStrategy selectionStrategy);
    abstract Individual match(Collection<Individual> individuals, Individual individual);
}
