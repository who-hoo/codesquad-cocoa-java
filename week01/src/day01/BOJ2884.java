package day01;

import java.util.Scanner;

public class BOJ2884 {
    static final int TARGET_MINUTES = 45;

    public static String setAlarm(int hh, int mm) {
        int aHour = hh;
        int aMinute = subMinute(mm);

        if (isBeforeTargetMinute(mm)) {
            aMinute += 60;
            aHour = subHour(hh);
        }
        return aHour + " " + aMinute;
    }

    public static int subMinute(int mm) {
        return mm - TARGET_MINUTES;
    }

    public static int subHour(int hh) {
        return isMidnight(hh) ? 23 : hh - 1;
    }

    public static boolean isBeforeTargetMinute(int mm) {
        return mm < TARGET_MINUTES;
    }

    public static boolean isMidnight(int hh) {
        return hh == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hh = sc.nextInt();
        int mm = sc.nextInt();
        sc.close();

        String result = setAlarm(hh, mm);
        System.out.println(result);
    }
}