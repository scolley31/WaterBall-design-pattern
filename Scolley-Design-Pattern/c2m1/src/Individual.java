import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

public class Individual {

    Collection<Individual> matchIndividuals = new HashSet<>();
    int id;
    Gender gender;
    int age;
    char[] intro;
    char[][] habit;
    Coord coord;

    public Individual(int id, Gender gender, int age, char[] intro, char[][] habit, Coord coord) {
        setId(id);
        setGender(gender);
        setAge(age);
        setIntro(intro);
        setHabit(habit);
        setCoord(coord);
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("id要大於0");
        this.id = id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        if (age <= 18) throw new IllegalArgumentException("age要大於18歲");
        this.age = age;
    }

    public void setIntro(char[] intro) {
        if (intro.length > 200) throw new IllegalArgumentException("請輸入 0-200 字元的自我介紹");
        this.intro = intro;
    }

    public void setHabit(char[][] habit) {
        this.habit = habit;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void addMatchIndividual(Individual matchIndividual) {
        matchIndividuals.add(matchIndividual);
    }
}
