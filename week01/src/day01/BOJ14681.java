package day01;

import java.util.Scanner;

public class BOJ14681 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.close();

        int result = (y > 0) ? 1 : 4;
        if (x < 0) result = (y > 0) ? 2 : 3;

        System.out.println(result);
    }
}
