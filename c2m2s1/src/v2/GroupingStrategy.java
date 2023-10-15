package v2;

import common.Group;
import common.Student;

import java.util.List;

public interface GroupingStrategy {
    public List<Group> group(List<Student> students);
}
