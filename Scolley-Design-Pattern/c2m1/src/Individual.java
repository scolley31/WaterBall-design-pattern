import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Individual {

    private Collection<Individual> matchIndividuals = new HashSet<>();
    private int id;
    private Gender gender;
    private int age;
    private char[] intro;
    private String[] habit;
    private Coord coord;

    public Individual(int id, Gender gender, int age, char[] intro, String[] habit, Coord coord) {
        setId(id);
        setGender(gender);
        setAge(age);
        setIntro(intro);
        setHabit(habit);
        setCoord(coord);
    }

    public int getId() {
        return id;
    }

    public String[] getHabit() {
        return habit;
    }

    public Coord getCoord() {
        return coord;
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

    public void setHabit(String[] habit) {
        this.habit = habit;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void addMatchIndividual(Individual matchIndividual) {
        System.out.println("id = " + this.id + " 與 id = " + matchIndividual.id + " 配對");
        matchIndividuals.add(matchIndividual);
    }

}
