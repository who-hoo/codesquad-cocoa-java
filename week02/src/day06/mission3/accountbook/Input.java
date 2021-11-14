package day06.mission3.accountbook;

import day06.mission3.accountbook.meta.PaymentType;
import java.util.*;

public class Input {

    public static final Scanner sc = new Scanner(System.in);

    private Input() {
        throw new IllegalStateException("Utility class");
    }

    public static void close() {
        sc.close();
    }

    public static String getString(String msg) {
        System.out.print(msg);
        String str = "";
        try {
            str = sc.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("no line was found.");
            str = sc.nextLine();
        } catch (IllegalStateException e) {
            System.out.println("scanner is closed.");
            str = sc.nextLine();
        }
        return str;
    }

    public static int getInteger(String msg) {
        System.out.print(msg);
        int n;
        try {
            String str = sc.nextLine().trim();
            if (str.contains(".")) {
                throw new InputException("소수를 입력하셨습니다. 정수를 입력하세요.");
            }
            if (!(Integer.MIN_VALUE < Long.parseLong(str)
                && Long.parseLong(str) < Integer.MAX_VALUE)) {
                throw new InputException(
                    Integer.MIN_VALUE + "~" + Integer.MAX_VALUE + " 범위 이내의 숫자를 입력하세요.");
            }
            n = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            n = getInteger(msg);
        } catch (InputException e) {
            System.out.println(e.getMessage());
            n = getInteger(msg);
        }
        return n;
    }

    public static PaymentType getPaymentType() {
        System.out.print("결제 타입(1: 현금, 2: 카드, 0: 결제안함)을 입력하세요 >>>>> ");
        PaymentType pt;
        try {
            pt = PaymentType.getMatchedPaymentType(sc.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("존재하지 않는 결제 타입(1: 현금, 2: 카드, 0: 결제안함)입니다.");
            pt = getPaymentType();
        }
        return pt;
    }

    public static String getYYYYMMDD() {
        System.out.print("날짜(yyyymmdd) >>>>> ");
        String yyyymmdd;
        try {
            yyyymmdd = sc.nextLine();
            validateYear(Integer.parseInt(yyyymmdd.substring(0, 4)));
            validateMonth(Integer.parseInt(yyyymmdd.substring(4, 6)));
            validateDay(Integer.parseInt(yyyymmdd.substring(6, 8)));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            yyyymmdd = getYYYYMMDD();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            yyyymmdd = getYYYYMMDD();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("yyyymmdd 형식으로 입력하세요.");
            yyyymmdd = getYYYYMMDD();
        }
        return yyyymmdd;
    }

    // ===============================================================================
    // validation check
    // ===============================================================================
    private static void validateYear(int year) {
        if (!(0 <= year && year < 10000)) {
            throw new InputMismatchException("연도(year)가 범위(0000~9999)를 벗어납니다.");
        }
    }

    private static void validateMonth(int month) {
        if (!(0 < month && month < 13)) {
            throw new InputMismatchException("월(month)이 범위(1~12)를 벗어납니다.");
        }
    }

    private static void validateDay(int day) {
        if (!(0 < day && day < 31)) {
            throw new InputMismatchException("일(day)이 범위(1~31)를 벗어납니다.");
        }
    }
}
