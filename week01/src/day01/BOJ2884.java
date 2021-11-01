package day01;

import java.util.Scanner;

public class BOJ2884 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hh = sc.nextInt();
        int mm = sc.nextInt();
        sc.close();

        mm = mm - 45;
        if (mm < 0) {
            mm = mm + 60;
            if (--hh < 0) hh = 23;
        }
        String result = hh + " " + mm;
        System.out.println(result);
    }
}