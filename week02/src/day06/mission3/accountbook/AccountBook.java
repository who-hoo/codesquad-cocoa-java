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
                    deleteContent(input.nextInt());
                    break;
                case "3":
                    System.out.print("수정할 데이터의 순번을 입력해주세요 >>>>> ");
                    updateContent(input.nextInt());
                    break;
                case "4":
                    printContents();
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
        String result = contents.remove(target) ? "success" : "fail";
        System.out.println(result);
    }

    void updateContent(int no) {
        // TODO: 데이터 수정 구현
        System.out.println("update" + no);
    }

    void printContents() {
        System.out.printf("[순번] 적요 수입 지출 잔액 %n");
        if (!contents.isEmpty()) {
            for (AccountData content : contents) {
                System.out.printf("[%d] %s %d %d %d %n"
                    , content.no, content.summary, content.income, content.expense,
                    AccountData.balance);
            }
        }
    }

    public static void main(String[] args) {
        AccountBook ab = new AccountBook();
        // create data for test
        ab.contents.add(new AccountData("20211108", "test1", 100, 0));
        ab.contents.add(new AccountData("20211108", "test2", 200, 0));
        ab.contents.add(new AccountData("20211108", "test3", 0, 100));

        ab.askUserAction();
        input.close();
    }
}
