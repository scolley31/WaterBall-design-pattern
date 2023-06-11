import java.util.Arrays;
import java.util.List;

public class Individual {

    private final Coords coords;
    private final int id;
    private final String gender;
    private final int age;
    private final String intro;
    private final List<String> habits;

    public Individual(Coords coords, int id, String gender, int age, String intro, String[] habits) {
        this.coords = coords;
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.intro = intro;
        this.habits = Arrays.asList(habits);
    }

    public Coords getCoords() {
        return this.coords;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getIntro() {
        return intro;
    }

    public List<String> getHabits() {
        return habits;
    }
}
