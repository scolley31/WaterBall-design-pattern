package v2;

import common.Group;
import common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeSlotsBasedGroupingStrategy implements GroupingStrategy {
    public static final int GROUP_MIN_SIZE = 6;
    @Override
    public List<Group> group(List<Student> students) {
        // 第一刀: 按照早中晚的空閒時間分組
        Map<String, Group> firstCut = new HashMap<>();
        for (Student student : students) {
            if (!firstCut.containsKey(hashMemberSlots(student))) {
                firstCut.put(hashMemberSlots(student), new Group());
            }

            firstCut.get(hashMemberSlots(student)).addStudent(student);
        }

        // 第二刀: 每組 6 人分組
        Map<Integer, Group> secondCut = new HashMap<>();
        int groupNum = 0;
        for (Group group : firstCut.values()) {
            for (int i = 0; i < group.size(); i++) {
                if (!secondCut.containsKey(groupNum)) {
                    secondCut.put(groupNum, new Group());
                } else if (secondCut.get(groupNum).size() >= GROUP_MIN_SIZE) {
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
        for (Group group : secondCut.values()) {
            if (group.size() < GROUP_MIN_SIZE) {
                NonFullGroups.add(group);
            } else {
                fullGroups.add(group);
            }
        }

        for (Group nonFullGroup : NonFullGroups) {
            for (Group fullGroup : fullGroups) {
                if (fullGroup.getStudents().size() == GROUP_MIN_SIZE) {
                    System.out.printf("merge group %d to group %d.\n", nonFullGroup.getNumber(), fullGroup.getNumber());
                    fullGroup.merge(nonFullGroup);
                    break;
                }
            }
        }

        return fullGroups;
    }


    private String hashMemberSlots(Student student) {
        // Hash to four s 9AM-12AM 1PM-4PM 5PM-7PM 8PM-9PM
        boolean[] s = student.getAvailableTimeSlots();
        return String.format("%d%d%d%d", convertBooleanToNumber(s[0] & s[1] & s[2] & s[3]),
                convertBooleanToNumber(s[4] & s[5] & s[6] & s[7]),
                convertBooleanToNumber(s[8] & s[9] & s[10]),
                convertBooleanToNumber(s[11] & s[12])
        );

    }

    private int convertBooleanToNumber(boolean bool) {
        return bool ? 1 : 0;
    }
}
