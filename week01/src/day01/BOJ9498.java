package day01;

import java.util.Scanner;

public class BOJ9498 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int score = sc.nextInt();
        sc.close();

        char result = 'F';
        if (90 <= score) {
            result = 'A';
        } else if (80 <= score) {
            result = 'B';
        } else if (70 <= score) {
            result = 'C';
        } else if (60 <= score) {
            result = 'D';
        }

        System.out.println(result);
    }
}
