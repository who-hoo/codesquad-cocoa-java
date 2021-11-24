package jungsuk.ch14.practice;

import java.util.Arrays;
import java.util.OptionalInt;

public class Exercise14_06 {

    public static void main(String[] args) {
        String[] strArr = {"aaa", "bb", "c", "dddd"};
        OptionalInt maxLength = Arrays.stream(strArr)
            .mapToInt(String::length)
            .max();
        System.out.println(maxLength.getAsInt());
    }
}
