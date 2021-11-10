package day06.mission3.accountbook;

import java.io.*;
import java.util.*;

public class Users {

    private final static String fileName = Users.class.getResource("").getPath() + "users.txt";
    private final static File file = new File(fileName);
    final static Set<User> users = new HashSet<>();

    Users() {
        getUsersFromFile();
    }

    private void getUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = null;
            while ((str = br.readLine()) != null) {
                String userName = str.split(":")[0];
                String userPassword = str.split(":")[1];
                User user = new User(userName, userPassword);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Users 초기화에 실패하였습니다.");
            e.printStackTrace();
        }
    }
}
