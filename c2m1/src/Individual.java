import java.util.Set;
import java.util.function.Predicate;

public class Individual {
    private int id;
    private int age;
    private Coord coord;
    private Gender gender;
    private Set<String> interests;
    private String intro;

    private MatchmakingSystem matchmakingSystem;

    public Individual(int id, int age, Coord coord, Gender gender, String interests, String intro) {
        this.id = id;
        setAge(age);
        this.coord = coord;
        this.gender = gender;
        this.interests = Set.of(interests.split(","));
        setIntro(intro);
    }

    public Gender getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public int getDistance(Individual matchedIndividual) {
        return (int) Math.sqrt(
                Math.pow(matchedIndividual.coord.getY() - this.coord.getY(), 2) + Math.pow(matchedIndividual.coord.getX() - this.coord.getX(), 2));
    }

    public void setMatchmakingSystem(MatchmakingSystem matchmakingSystem) {
        this.matchmakingSystem = matchmakingSystem;
    }

    public MatchmakingSystem getMatchmakingSystem() {
        return matchmakingSystem;
    }

    public Set<String> getInterests() {
        return interests;
    }

    static Predicate<Individual> getAnotherGender(Individual matchingIndividual) {
        return individual -> individual.getGender() != matchingIndividual.getGender();
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be greater than 18");
        }
        this.age = age;
    }

    public void setIntro(String intro) {
        if (intro.length() > 200) {
            throw new IllegalArgumentException("Intro must be less than 200 characters");
        }
        this.intro = intro;
    }
}
