package v1;

import common.Group;
import common.ReadStudents;
import common.Student;

import java.io.IOException;
import java.util.List;

import static common.Util.printGroups;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Student> students = ReadStudents.fromFile("./src/student.data");

        LanguageBasedGroupingStrategy languageBasedGroupingStrategy = new LanguageBasedGroupingStrategy();
        List<Group> groups = languageBasedGroupingStrategy.group(students);

        printGroups(groups);
    }

}