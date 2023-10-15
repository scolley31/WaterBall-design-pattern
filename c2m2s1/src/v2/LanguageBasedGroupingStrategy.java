package v2;

import common.Group;
import common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageBasedGroupingStrategy implements GroupingStrategy{
    public static final int GROUP_MIN_SIZE = 6;
    @Override
    public List<Group> group(List<Student> students) {
        // 第一刀: 程式語言專長
        Map<String, Group> firstCut = new HashMap<>();
        for (Student student: students) {
            String language = student.getLanguage();
            if(!firstCut.containsKey(language)) {
                firstCut.put(language, new Group());
            }

            firstCut.get(language).addStudent(student);
        }

        // 第二刀: 每組 6 人分組
        Map<Integer, Group> secondCut = new HashMap<>();
        int groupNum = 0;
        for(Group group: firstCut.values()){
            for (int i = 0; i < group.size(); i++) {
                if(!secondCut.containsKey(groupNum)){
                    secondCut.put(groupNum, new Group());
                }
                else if(secondCut.get(groupNum).size() >= GROUP_MIN_SIZE) {
                    groupNum++;
                    secondCut.put(groupNum, new Group());
                }

                secondCut.get(groupNum).addStudent(group.getStudents().get(i));
            }

            groupNum++;
        }

        // 將不足的6人的組別補到前面的組別
        List<Group> fullGroups = new ArrayList<>();
        List<Group> NonFullGroups = new ArrayList<>();
        for(Group group : secondCut.values()){
            if(group.size() < GROUP_MIN_SIZE) {
                NonFullGroups.add(group);
            }
            else{
                fullGroups.add(group);
            }
        }

        for (Group nonFullGroup : NonFullGroups) {
            for (Group fullGroup : fullGroups) {
                if(fullGroup.getStudents().getFirst().getLanguage().equals(nonFullGroup.getStudents().getFirst().getLanguage())){
                    System.out.printf("merge group %d to group %d.\n", nonFullGroup.getNumber(), fullGroup.getNumber());
                    fullGroup.merge(nonFullGroup);
                    break;
                }
            }
        }

        return fullGroups;
    }
}
