package day06.mission3.accountbook;

import day06.mission3.accountbook.meta.PaymentType;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    public static final Scanner input = new Scanner(System.in);

    public static void close() {
        input.close();
    }

    public static String getString(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }

    public static int getInteger(String msg) {
        System.out.print(msg);
        int n;
        try {
            n = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            n = getInteger(msg);
        }
        return n;
    }

    public static PaymentType getPaymentType() {
        System.out.print("결제 타입(1: 현금, 2: 카드, 0: 결제안함)을 입력하세요 >>>>> ");
        PaymentType pt;
        try {
            pt = PaymentType.getMatchedPaymentType(input.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("존재하지 않는 결제 타입(1: 현금, 2: 카드, 0: 결제안함)입니다.");
            pt = getPaymentType();
        }
        return pt;
    }

    public static void validateYear(int year) {
        if (!(0 <= year && year < 10000)) {
            throw new InputMismatchException("연도(year)가 범위(0000~9999)를 벗어납니다.");
        }
    }

    public static void validateMonth(int month) {
        if (!(0 < month && month < 13)) {
            throw new InputMismatchException("월(month)이 범위(1~12)를 벗어납니다.");
        }
    }

    public static void validateDay(int day) {
        if (!(0 < day && day < 31)) {
            throw new InputMismatchException("일(day)이 범위(1~31)를 벗어납니다.");
        }
    }

    public static String getYYYYMMDD() {
        System.out.print("날짜(yyyymmdd) >>>>> ");
        String yyyymmdd;
        try {
            yyyymmdd = input.nextLine();
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
}
