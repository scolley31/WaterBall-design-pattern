import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 初始化資料
        ArrayList<Individual> individuals = new ArrayList<>(Arrays.asList(
                new Individual(Individual.Gender.MALE, 20,"Alex", new ArrayList<>(Arrays.asList("A", "B", "C")), new Coordinate(0,0)),
                new Individual(Individual.Gender.FEMALE, 18,"Jenny", new ArrayList<>(Arrays.asList("A", "B")), new Coordinate(10,0)),
                new Individual(Individual.Gender.MALE, 30,"Max", new ArrayList<>(Arrays.asList("B", "C", "D")), new Coordinate(15,0)),
                new Individual(Individual.Gender.MALE, 23,"Mike", new ArrayList<>(Arrays.asList("A", "B", "D")), new Coordinate(15,0)),
                new Individual(Individual.Gender.FEMALE, 18,"Aurora", new ArrayList<>(Arrays.asList("E", "F", "G")), new Coordinate(5,2)),
                new Individual(Individual.Gender.MALE, 35,"Amy", new ArrayList<>(Arrays.asList("E", "F", "G")), new Coordinate(5,2))
        ));

        // 設定策略
        MatchStrategy matchStrategy = new HabitBasedStrategy();
        SelectionStrategy selectionStrategy = new SimilarSelectionStrategy();
        matchStrategy.setSelectionStrategy(selectionStrategy);

        // 執行配對
        MatchMakingSystem matchMakingSystem = new MatchMakingSystem(individuals, matchStrategy);
        matchMakingSystem.match(individuals.get(0));
    }
}