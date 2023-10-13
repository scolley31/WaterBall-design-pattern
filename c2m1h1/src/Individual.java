import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class Individual {
    private static int counter = 0;
    private int id;
    private Gender gender;
    private int age;
    private String intro;
    private ArrayList<String> habits;
    private Coordinate coord;

    public Individual(Gender gender, int age, String intro, ArrayList<String> habits, Coordinate coord) {
        counter++;
        setId(counter);
        setGender(gender);
        setAge(age);
        setIntro(intro);
        setHabits(habits);
        setCoord(coord);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = requireNonNull(gender);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be older than 18.");
        }
        this.age = age;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        if (intro.length() > 200) {
            throw new IllegalArgumentException("The content of introduction cannot longer than 200 characters.");
        }

        this.intro = intro;
    }

    public ArrayList<String> getHabits() {
        return habits;
    }

    public void setHabits(ArrayList<String> habits) {
        if (habits.stream().anyMatch(habit -> habit.length() > 10 || habit.isEmpty())) {
            throw new IllegalArgumentException("Habit must be between 1 to 10 characters.");
        }
        this.habits = requireNonNull(habits);
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = requireNonNull(coord);
    }

    @Override
    public String toString() {
        return String.format("Id: %d\nIntroduction: %s\nGender: %s\nAge: %d\nHabits: %s\nCoordinate: %s\n", getId(), getIntro(), getGender(), getAge(), String.join(",", getHabits()), getCoord());
    }

    public enum Gender {
        MALE, FEMALE
    }
}
