package day06.mission3.accountbook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    static Scanner input = new Scanner(System.in);

    void close() {
        input.close();
    }

    String getString(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }

    int getInteger(String msg) {
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

    void validateYear(int year) {
        if (!(0 <= year && year < 10000)) {
            throw new InputMismatchException("연도(year)가 범위(0000~9999)를 벗어납니다.");
        }
    }

    void validateMonth(int month) {
        if (!(0 < month && month < 13)) {
            throw new InputMismatchException("월(month)이 범위(1~12)를 벗어납니다.");
        }
    }

    void validateDay(int day) {
        if (!(0 < day && day < 31)) {
            throw new InputMismatchException("일(day)이 범위(1~31)를 벗어납니다.");
        }
    }

    String getYYYYMMDD() {
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
