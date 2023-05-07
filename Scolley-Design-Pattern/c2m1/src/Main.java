import java.util.Collection;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Collection<Individual> individuals = new HashSet<>();

        Individual mine = new Individual(1, Gender.MALE, 19, "Scolley".toCharArray(), new char[][]{"baseball".toCharArray(), "CS".toCharArray()}, new Coord(0.0, 0.0));
        Individual individual1 = new Individual(2, Gender.FEMALE, 20, "A".toCharArray(), new char[][]{"piano".toCharArray(), "basketball".toCharArray()}, new Coord(1.0, 1.0));
        Individual individual2 = new Individual(3, Gender.FEMALE, 30, "B".toCharArray(), new char[][]{"baseball".toCharArray(), "CS".toCharArray()}, new Coord(2.0, 2.0));

        individuals.add(individual1);
        individuals.add(individual2);


        MatchMakingSystem matchMakingSystem = new MatchMakingSystem.Builder()
                .setYourSelf(mine)
                .setIndividuals(individuals)
                .setMatchingMakingStrategy(new ReverseStrategy(new DistanceBasedStrategy()))
                .build();

        matchMakingSystem.match();

    }
}