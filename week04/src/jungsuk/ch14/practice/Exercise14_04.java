package jungsuk.ch14.practice;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class Exercise14_04 {

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> isTotalSix = (a, b) -> a + b == 6;
        BiConsumer<Integer, Integer> print = (a, b) -> System.out.println("[" + a + ", " + b + "]");
        int[] dice1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] dice2 = new int[]{1, 2, 3, 4, 5, 6};

        for (int d1 : dice1) {
            for (int d2 : dice2) {
                if (isTotalSix.test(d1, d2)) {
                    print.accept(d1, d2);
                }
            }
        }
    }
}
