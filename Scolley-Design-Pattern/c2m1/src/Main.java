import java.util.Collection;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Collection<Individual> individuals = new HashSet<>();

        Individual mine = new Individual(1, Gender.MALE, 19, "Scolley".toCharArray(), new String[]{"baseball", "CS"}, new Coord(0.0, 0.0));
        Individual individual1 = new Individual(2, Gender.FEMALE, 20, "A".toCharArray(), new String[]{"baseball", "CS"}, new Coord(3.0, 1.0));
        Individual individual2 = new Individual(3, Gender.FEMALE, 30, "B".toCharArray(), new String[]{"piano", "basketball"}, new Coord(1.0, 1.0));
        Individual individual3 = new Individual(4, Gender.MALE, 33, "C".toCharArray(), new String[]{"piano", "baseball"}, new Coord(1.0, 1.0));

        individuals.add(individual1);
        individuals.add(individual3);
        individuals.add(individual2);

        MatchMakingSystem matchMakingSystem = new MatchMakingSystem.Builder()
                .setYourSelf(mine)
                .setIndividuals(individuals)
                .setMatchingMakingStrategy(new HabitBasedStrategy())
                .build();

        matchMakingSystem.match();

    }
}