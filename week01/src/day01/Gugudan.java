package day01;

import java.util.*;

class Gugudan {
    private int from;
    private int to;

    Gugudan() {
        this(1, 9);
    }

    Gugudan(int from, int to) {
        this.from = from;
        this.to = to;
    }

    void printNdan(int n) {
        System.out.println("===== " + n + "단 =====");
        for (int i = 1; i < 10; i++) {
            System.out.printf("%d * %d = %d %n", n, i, n * i);
        }
    }

    void printAll() {
        for (int i = from; i <= to; i++) {
            printNdan(i);
        }
    }
}

class GugudanTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int from, to = 0;

        while (true) {
            System.out.print("시작 단 : ");
            from = sc.nextInt();
            if (from > 0) break;
            System.out.println("자연수를 입력해주세요.");
        }

        while (true) {
            System.out.print("끝 단 : ");
            to = sc.nextInt();
            if (from < to) break;
            System.out.println("시작 단보다 큰 값을 입력해주세요.");
        }
        sc.close();
        Gugudan gugu = new Gugudan(from, to);
        gugu.printAll();
    }
}