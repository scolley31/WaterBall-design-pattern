package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Group {
    private static int numberCount = 0;
    private final int number;

    public Group() {
        this.number = numberCount++;
    }

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(Objects.requireNonNull(student));
    }

    public int size() {
        return students.size();
    }

    public int getNumber() {
        return number;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student get(int index) {
        return students.get(index);
    }

    public void merge(Group group) {
        this.students.addAll(group.students);
    }

    @Override
    public String toString() {
        return students.stream().map(student -> {
            boolean[] availableTimeSlots = student.getAvailableTimeSlots();
            String timeSlots = IntStream.range(0, availableTimeSlots.length).filter(i -> availableTimeSlots[i]).mapToObj(i-> String.valueOf(i+9)).collect(Collectors.joining(" ", "[", "]"));

            return String.format("%s %s %sy %s %s", student.getName(), student.getLanguage(), student.getExperience(), student.getJobTitle(), timeSlots);
        }).collect(Collectors.joining(",\n"));
    }
}
