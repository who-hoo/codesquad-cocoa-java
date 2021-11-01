package day01;

import java.util.Scanner;

public class BOJ1330 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        String result = "==";
        if (a > b) result = ">";
        else if (a < b) result = "<";

        System.out.println(result);
    }
}
