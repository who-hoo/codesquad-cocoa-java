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

    public static void main(String[] args) {
        AccountBook ab = new AccountBook();
        System.out.println("user name : " + ab.user.getName());
        System.out.println("user password: " + ab.user.getPassword());
        input.close();
    }
}
