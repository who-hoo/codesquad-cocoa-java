package jungsuk.ch14.practice;

public class Exercise14_02 {

    public static void main(String[] args) {
        /*
         * 람다식을 메서드 참조로 변환하시오. (변환이 불가능한 경우, '변환불가')
         *
         * (01) (String s) -> s.length()
         *     => String::length
         * (02) () -> new int[]{}
         *     => int[]::new
         * (03) arr -> Arrays.stream(arr)
         *     => Arrays::stream
         * (04) (String str1, String str2) -> str1.equals(str2)
         *     => String::equals
         * (05) (a, b) -> Integer.compare(a, b)
         *     => Integer::compare
         * (06) (String kind, int num) -> new Card(kind, num)
         *     => Card::new
         * (07) (x) -> System.out.println(x)
         *     => System.out::println
         * (08) () -> Math.random()
         *     => Math::random
         * (09) (str) -> str.toUpperCase()
         *     => String::toUpperCase
         * (10) () -> new NullPointerException()
         *     => NullPointerException::new
         * (11) (Optional opt) -> opt.get()
         *     => Optional::get
         * (12) (StringBuffer sb, String s) -> sb.append(s)
         *     => StringBuffer::append
         * (13) (String s) -> System.out.println(s)
         *     => System.out::println
         */
    }
}
