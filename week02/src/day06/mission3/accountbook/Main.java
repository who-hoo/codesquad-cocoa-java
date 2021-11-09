package day06.mission3.accountbook;

import java.util.*;

public class Main {

    private static final HashSet<User> users = new HashSet<>();
    private static final ArrayList<AccountBook> books = new ArrayList<>();

    private boolean isRunning = true;

    public void run() {
        while (isRunning) {
            String action = getAction();
            execAction(action);
        }
    }

    private String getAction() {
        return Input.getString("press (1: 사용자등록, 2: 로그인, 0: 종료) + enter >>>>> ");
    }

    private void execAction(String action) {
        switch (action) {
            case "1":
                userRegister();
                break;
            case "2":
                signIn();
                break;
            case "0":
                isRunning = false;
                Input.close();
                break;
            default:
                break;
        }
    }

    private void userRegister() {
        String userName = Input.getString("input your name >>>>> ");
        String userPassword = Input.getString("input your password >>>>> ");
        User user = new User(userName, userPassword);
        boolean isSuccessful = users.add(user) && books.add(new AccountBook(user));
        String result = isSuccessful ? "사용자 등록을 완료하였습니다." : "이미 존재하는 사용자입니다.";
        System.out.println(result);
    }

    private boolean checkId(String userName) {
        boolean isValid = users.stream()
            .anyMatch(user -> user.getName().equals(userName));
        if (!isValid) {
            System.out.println("존재하지 않는 사용자입니다.");
        }
        return isValid;
    }

    private boolean checkPassword(String userName, String userPassword) {
        boolean isValid = users.stream()
            .anyMatch(
                user -> user.getName().equals(userName) && user.getPassword().equals(userPassword));
        if (!isValid) {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
        return isValid;
    }

    private void signIn() {
        String userName = Input.getString("input your name >>>>> ");
        String userPassword = Input.getString("input your password >>>>> ");

        if (checkId(userName) && checkPassword(userName, userPassword)) {
            books.stream()
                .filter(book -> book.getUserName().equals(userName))
                .forEach(AccountBook::run);
        } else {
            System.out.println("로그인에 실패하였습니다.");
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
