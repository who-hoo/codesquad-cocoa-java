package day01;

import java.util.Scanner;

public class BOJ11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numLength = Integer.parseInt(sc.nextLine());
        String num = sc.nextLine();
        sc.close();

        int result = 0;
        for (int i = 0; i < numLength; i++) {
            result += Character.digit(num.charAt(i), 10);
        }

        System.out.println(result);
    }
}
