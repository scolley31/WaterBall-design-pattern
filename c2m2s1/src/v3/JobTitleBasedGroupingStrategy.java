package v3;

import common.Group;
import common.Student;

public class JobTitleBasedGroupingStrategy extends CutBasedGroupingStrategy{
    @Override
    protected Object cutBy(Student student) {
        return student.getJobTitle();
    }

    @Override
    protected boolean meetMergeCriteria(Group nonFullGroup, Group fullGroup) {
        return nonFullGroup.get(0).getJobTitle().equals(fullGroup.get(0).getJobTitle());
    }
}
