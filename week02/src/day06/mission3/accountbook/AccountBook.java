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
        // TODO: 데이터 생성 구현
        System.out.println("create");
    }

    void deleteContent(int no) {
        // TODO: 데이터 삭제 구현
        System.out.println("delete" + no);
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
        ab.askUserAction();
        input.close();
    }
}
