package day06.mission3.accountbook;

import java.util.*;

public class Main {

    static HashSet<User> users = new HashSet<>();
    static ArrayList<AccountBook> books = new ArrayList<>();
    static Input input = new Input();

    boolean isRunning = true;

    void run() {
        while (isRunning) {
            String action = getAction();
            execAction(action);
        }
    }

    String getAction() {
        return input.getString("press (1: 사용자등록, 2: 로그인, 0: 종료) + enter >>>>> ");
    }

    void execAction(String action) {
        switch (action) {
            case "1":
                userRegister();
                break;
            case "2":
                signIn();
                break;
            case "0":
                isRunning = false;
                input.close();
                break;
            default:
                break;
        }
    }

    void userRegister() {
        String userName = input.getString("input your id >>>>> ");
        String userPassword = input.getString("input your password >>>>> ");
        User user = new User(userName, userPassword);
        String result = users.add(user) && books.add(new AccountBook(user))
            ? "사용자 등록을 완료하였습니다." : "이미 존재하는 사용자입니다.";
        System.out.println(result);
    }

    void signIn() {
        String userName = input.getString("input your id >>>>> ");
        String userPassword = input.getString("input your password >>>>> ");

        boolean signInSuccess = users.stream()
            .anyMatch(
                user -> user.getName().equals(userName) && user.getName().equals(userPassword));
        if (signInSuccess) {
            books.stream()
                .filter(book -> book.user.getName().equals(userName))
                .forEach(AccountBook::run);
        } else {
            System.out.println("로그인에 실패하였습니다.");
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
