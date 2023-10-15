package common;

import java.util.List;

public class Util {
    public static void printGroups(List<Group> groups) {
    for (Group group : groups) {
        System.out.print("[");
        for (int i = 0; i < group.size(); i++) {
            if (i % 2 == 0) {
                System.out.print(group.get(i));
            } else {
                System.out.printf(", %s", group.get(i));
                if (i != group.size() - 1) {
                    System.out.println();
                }
            }
        }
        System.out.print("]\n\n");
    }
}
}
