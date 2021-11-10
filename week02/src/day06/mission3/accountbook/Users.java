package day06.mission3.accountbook;

import java.io.*;
import java.util.*;

public class Users {

    private final static String fileName = Users.class.getResource("").getPath() + "users.txt";
    private final static File file = new File(fileName);
    private final static Set<User> users = new HashSet<>();

    Users() {
        getUsersFromFile();
    }

    private void getUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = null;
            while ((str = br.readLine()) != null) {
                String userName = str.split(":")[0];
                String userPassword = str.split(":")[1];
                users.add(new User(userName, userPassword));
            }
        } catch (IOException e) {
            System.out.println("Users 초기화에 실패하였습니다.");
            e.printStackTrace();
        }
    }

    public boolean signUp(User user) {
        // TODO: 파일에 추가
        return users.add(user);
    }

    public boolean signIn(String userName, String userPassword) {
        return isExistUser(userName) && isValidPassword(userName, userPassword);
    }

    private boolean isExistUser(String userName) {
        boolean isExist = users.stream()
            .anyMatch(user -> user.correctName(userName));
        if (!isExist) {
            System.out.println("존재하지 않는 사용자입니다.");
        }
        return isExist;
    }

    private boolean isValidPassword(String userName, String userPassword) {
        boolean isValid = users.stream()
            .anyMatch(
                user -> user.correctName(userName) && user.correctPassword(userPassword));
        if (!isValid) {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
        return isValid;
    }
}
