package com.booking;

public class ShiftingString {
    public static String getShiftedString(String s, int leftShifts, int rightShifts) {
        // Write your code here
        int length = s.length();
        int totalLeftShifts = leftShifts - rightShifts;
        totalLeftShifts = totalLeftShifts < 0
                ? length - (Math.abs(totalLeftShifts) % length)
                : totalLeftShifts % length;

        StringBuilder sb = new StringBuilder();

        for (int i = totalLeftShifts; i < totalLeftShifts + length; i++) {
            int currIdx = i % length;
            sb.append(s.charAt(currIdx));
        }

        return sb.toString();
    }
}
