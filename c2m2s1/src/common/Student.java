package common;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Student {
    private final String name;

    private final int experience;
    private final String language;
    private final String jobTitle;
    private final boolean[] availableTimeSlots;

    public Student(String name, int experience, String language, String jobTitle, boolean[] availableTimeSlots) {
        this.name = name;
        this.experience = experience;
        this.language = language;
        this.jobTitle = jobTitle;
        this.availableTimeSlots = availableTimeSlots;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public String getLanguage() {
        return language;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean[] getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    @Override
    public String toString() {
        String timeSlots = IntStream.range(0, getAvailableTimeSlots().length)
                .filter(i -> availableTimeSlots[i])
                .mapToObj(i -> String.valueOf(i + 9))
                .collect(Collectors.joining(" "));

        return String.format("%s %s %sy %s [%s]", getName(), getLanguage(), getExperience(), getJobTitle(), timeSlots);
    }
}
