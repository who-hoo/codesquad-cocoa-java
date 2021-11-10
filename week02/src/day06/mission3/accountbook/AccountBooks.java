package day06.mission3.accountbook;

import java.io.*;
import java.util.*;

public class AccountBooks {

    private final static String fileName = Users.class.getResource("").getPath() + "users.txt";
    private final static File file = new File(fileName);
    private final static List<AccountBook> books = new ArrayList<>();

    AccountBooks() {
        generateAccountBooksFromFile();
    }

    private void generateAccountBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = null;
            while ((str = br.readLine()) != null) {
                String userName = str.split(":")[0];
                String userPassword = str.split(":")[1];
                User user = new User(userName, userPassword);
                books.add(new AccountBook(user));
            }
        } catch (IOException e) {
            System.out.println("AccountBooks 초기화에 실패하였습니다.");
            e.printStackTrace();
        }
    }

    public boolean create(User user) {
        return books.add(new AccountBook(user));
    }

    public void run(String username) {
        books.stream()
            .filter(book -> book.of(username))
            .forEach(AccountBook::run);
    }
}
