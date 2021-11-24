package jungsuk.ch14.practice;

public class Exercise14_01 {

    int max(int a, int b) {
        return a > b ? a : b;
    }

    void printVar(String name, int i) {
        System.out.println(name + "=" + i);
    }

    int square(int x) {
        return x * x;
    }

    int roll() {
        return (int) (Math.random() * 6);
    }

    int sumArr(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    int[] emptyArr() {
        return new int[]{};
    }

    public static void main(String[] args) {
        /*
         * 메서드를 람다식으로 변환하시오.
         *
         * (1) max
         * (int a, int b) -> a > b ? a : b
         *
         * (2) printVar
         * (String name, int i) -> System.out.println(name+"="+i)
         *
         * (3) square
         * x -> x * x
         *
         * (4) roll
         * () -> (int) (Math.random() * 6)
         *
         * (5) sumArr
         * (int[] arr) -> {
         *     int sum = 0;
         *     for (int i : arr) {
         *         sum += i;
         *     }
         *     return sum;
         * }
         *
         * (6) emptyArr
         * () -> new int[]{}
         */
    }
}
