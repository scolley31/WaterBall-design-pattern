import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

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
        System.out.println("id = " + this.id + " 與 id = " + matchIndividual.id + " 配對");
        matchIndividuals.add(matchIndividual);
    }

    public int habitBasedMatchCount(Individual individual) {

        int matchHabitCount = 0;
        HashSet<String> matchHabit = new HashSet<>();

        for (char[] chars : habit) {
            matchHabit.add(Arrays.toString(chars));
        }

        for (int i = 0; i < individual.habit.length; i++) {
            if (matchHabit.contains(Arrays.toString(individual.habit[i]))) matchHabitCount++;
        }

        return matchHabitCount;
    }

}
