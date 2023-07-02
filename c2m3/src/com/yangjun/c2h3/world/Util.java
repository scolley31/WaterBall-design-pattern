package com.yangjun.c2h3.world;

import java.util.Arrays;

public class Util {
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    
    public static String getCollisionCondition(Sprite s1, Sprite s2) {
        char[] charArr = new char[] {s1.getShowChar(), s2.getShowChar()};
        Arrays.sort(charArr);
        return new String(charArr);
    }
    
    public static String rightPadding(String str, int length) {
		if (str == null) {
			str = "";
		}
		if (str.length() >= length) {
			return str;
		}
		String pattern = "%-" + length + "s";
		return String.format(pattern, str);
	}
    
    public static String centerPadding(String str, int length) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str;
        }

        int totalPadding = length - str.length();
        int paddingLeft = totalPadding / 2;
        int paddingRight = totalPadding - paddingLeft;

        String leftPattern = "%" + paddingLeft + "s";
        String rightPattern = "%-" + paddingRight + "s";

        String leftPadded = String.format(leftPattern, "");
        String rightPadded = String.format(rightPattern, "");

        return leftPadded + str + rightPadded;
    }
}
