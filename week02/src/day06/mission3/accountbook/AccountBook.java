package day06.mission3.accountbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountBook {

    static Scanner input = new Scanner(System.in);

    User user;
    ArrayList<AccountData> contents;

    AccountBook() {
        this.user = userRegister();
        this.contents = new ArrayList<>(30);
    }

    User userRegister() {
        System.out.print("input your id >>>>> ");
        String userName = input.nextLine();
        System.out.print("input your password >>>>> ");
        String userPassword = input.nextLine();

        return new User(userName, userPassword);
    }

    void askUserAction() {
        boolean flag = true;
        while (flag) {
            System.out.print("press (1: 입력, 2: 삭제, 3: 수정, 4: 출력, 0: 종료) + enter >>>>> ");
            switch (input.nextLine()) {
                case "1":
                    createContent();
                    break;
                case "2":
                    System.out.print("삭제할 데이터의 순번을 입력해주세요 >>>>> ");
                    deleteContent(Integer.parseInt(input.nextLine()));
                    break;
                case "3":
                    System.out.print("수정할 데이터의 순번을 입력해주세요 >>>>> ");
                    updateContent(Integer.parseInt(input.nextLine()));
                    break;
                case "4":
                    System.out.print("출력할 대상 월을 입력해주세요(0: 전체) >>>>> ");
                    printContents(Integer.parseInt(input.nextLine()));
                    break;
                case "0":
                    flag = false;
                    break;
            }
        }
    }

    void createContent() {
        System.out.print("날짜(yyyymmdd) >>>>> ");
        String date = input.nextLine();
        System.out.print("적요 >>>>> ");
        String summary = input.nextLine();
        System.out.print("수입 >>>>> ");
        int income = Integer.parseInt(input.nextLine());
        System.out.print("지출 >>>>> ");
        int expense = Integer.parseInt(input.nextLine());

        contents.add(new AccountData(date, summary, income, expense));
    }

    AccountData findOne(int no) {
        for (AccountData content : contents) {
            if (content.no == no) {
                return content;
            }
        }
        return null;
    }

    void deleteContent(int no) {
        AccountData target = findOne(no);
        if (target == null) {
            System.out.println("해당 순번의 데이터가 존재하지 않습니다.");
            return;
        }
        String result = contents.remove(target) ? "delete success" : "delete fail";
        System.out.println(result);
    }

    void updateContent(int no) {
        AccountData target = findOne(no);
        if (target == null) {
            System.out.println("해당 순번의 데이터가 존재하지 않습니다.");
            return;
        }
        int targetIndex = contents.indexOf(target);

        System.out.print("날짜(yyyymmdd) >>>>> ");
        String date = input.nextLine();
        target.date = date;

        System.out.print("적요 >>>>> ");
        String summary = input.nextLine();
        target.summary = summary;

        System.out.print("수입 >>>>> ");
        int income = Integer.parseInt(input.nextLine());
        target.income = income;

        System.out.print("지출 >>>>> ");
        int expense = Integer.parseInt(input.nextLine());
        target.expense = expense;

        AccountData result = contents.set(targetIndex, target);
        System.out.printf("update success : [%d] %s %s %d %d %n"
            , result.no, result.date, result.summary, result.income, result.expense);
    }

    void printContents(int month) {
        System.out.println("========== " + (month == 0 ? "전체" : month) + " 월의 지출내역 출력 ==========");
        System.out.printf("[순번] 날짜 적요 수입 지출 잔액 %n");
        contents.stream()
            .filter(
                content -> Integer.parseInt(content.date.substring(4, 6)) == (month == 0 ?
                    Integer.parseInt(content.date.substring(4, 6)) : month))
            .forEach(content -> System.out.printf("[%d] %s %s %d %d %d %n"
                , content.no, content.date, content.summary, content.income, content.expense,
                AccountData.balance));
    }

    public static void main(String[] args) {
        AccountBook ab = new AccountBook();
        ab.askUserAction();
        input.close();
    }
}
