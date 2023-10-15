package v3;

import common.Group;
import common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeSlotsBasedGroupingStrategy extends CutBasedGroupingStrategy {
    @Override
    protected Object cutBy(Student student) {
        return hashMemberSlots(student);
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
