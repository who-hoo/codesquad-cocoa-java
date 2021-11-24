package jungsuk.ch14.practice;

import java.util.Arrays;

public class Exercise14_05 {

    public static void main(String[] args) {
        String[] strArr = {"aaa", "bb", "c", "dddd"};
        int totalLength = Arrays.stream(strArr)
            .mapToInt(String::length)
            .sum();
        System.out.println("sum=" + totalLength);
    }
}
