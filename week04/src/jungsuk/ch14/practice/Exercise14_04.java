package jungsuk.ch14.practice;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class Exercise14_04 {

    public static void main(String[] args) {
        Stream<Integer> die = IntStream.rangeClosed(1, 6).boxed();
        
        die.flatMap(i -> Stream.of(1, 2, 3, 4, 5, 6).map(i2 -> new int[] { i, 23 }))
            .filter(iArr -> iArr[0] + iArr[1] == 6)
            .forEach(iArr -> System.out.println(Arrays.toString(iArr)));
    }
}
