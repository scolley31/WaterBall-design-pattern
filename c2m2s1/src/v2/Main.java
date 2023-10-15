package v2;

import common.Group;
import common.ReadStudents;
import common.Student;

import java.io.IOException;
import java.util.List;

import static common.Util.printGroups;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Student> students = ReadStudents.fromFile("./src/student.data");

        GroupingStrategy groupingStrategy = new TimeSlotsBasedGroupingStrategy();
        List<Group> groups = groupingStrategy.group(students);

        printGroups(groups);
    }
}
