package day06.mission3.accountbook;

import java.util.*;

public class Main {

    private final Users userStore = new Users();
    private final AccountBooks bookStore = new AccountBooks();
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
                String userName = Input.getString("input your name >>>>> ");
                String userPassword = Input.getString("input your password >>>>> ");
                if (userStore.signIn(userName, userPassword)) {
                    bookStore.run(userName);
                } else {
                    System.out.println("로그인에 실패하였습니다.");
                }
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
        boolean isSuccessful = userStore.signUp(user) && bookStore.create(user);
        String result = isSuccessful ? "사용자 등록을 완료하였습니다." : "이미 존재하는 사용자입니다.";
        System.out.println(result);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
