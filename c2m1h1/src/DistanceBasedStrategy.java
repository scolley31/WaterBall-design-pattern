import java.util.Collection;
import java.util.Comparator;

public class DistanceBasedStrategy extends MatchStrategy{
    private SelectionStrategy selectionStrategy = new SimilarSelectionStrategy();

    public void setSelectionStrategy(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
    @Override
    public Individual match(Collection<Individual> individuals, Individual toBeMatched) {
        Comparator<Individual> comparator = Comparator.<Individual>comparingDouble(matched-> {
            Coordinate matchedCoord = matched.getCoord();
            Coordinate toBeMatchedCoord = toBeMatched.getCoord();
            return Math.sqrt(Math.pow(matchedCoord.getX() - toBeMatchedCoord.getX(),2) +  Math.pow(matchedCoord.getY() - toBeMatchedCoord.getY(),2) );
        }).thenComparingInt(Individual::getId);

        Collection<Individual> targets = individuals.stream().filter(target -> target!= toBeMatched).toList();

        return selectionStrategy.select(targets, comparator);
    }
}
