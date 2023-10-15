package v3;

import common.Group;
import common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageBasedGroupingStrategy extends CutBasedGroupingStrategy {
    @Override
    protected Object cutBy(Student student) {
        return student.getLanguage();
    }

    @Override
    protected boolean meetMergeCriteria(Group nonFullGroup, Group fullGroup){
        return fullGroup.getStudents().getFirst().getLanguage().equals(nonFullGroup.getStudents().getFirst().getLanguage());
    }
}
