package day06.mission3.accountbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AccountBooks {

    private static final String fileName =
        AccountBooks.class.getResource("").getPath() + "books.txt";
    private static final File file = new File(fileName);
    private static final List<AccountBook> books = new ArrayList<>();

    AccountBooks() {
        generateAccountBooksFromFile();
    }

    private void generateAccountBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(Users.file))) {
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

    public static void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            String str = books.stream()
                .map(AccountBook::toFileFormatString)
                .collect(Collectors.joining());
            bw.write(str);
        } catch (IOException e) {
            System.out.println("AccountBooks 저장에 실패하였습니다.");
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
