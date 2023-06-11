public class IndividualBuilder {
    private Coords coords;
    private int id;
    private String gender;
    private int age;
    private String intro;
    private String[] habits;

    public IndividualBuilder setCoords(Coords coords) {
        this.coords = coords;
        return this;
    }

    public IndividualBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public IndividualBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public IndividualBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public IndividualBuilder setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public IndividualBuilder setHabits(String[] habits) {
        this.habits = habits;
        return this;
    }

    public Individual createIndividual() {
        return new Individual(coords, id, gender, age, intro, habits);
    }
}