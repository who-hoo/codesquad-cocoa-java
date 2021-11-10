package day06.mission3.accountbook;

import day06.mission3.accountbook.meta.PaymentType;
import java.util.ArrayList;

public class AccountBook {

    private final User user;
    private final ArrayList<AccountData> contents;
    private int generateNo = 0;
    private boolean isRunning = true;

    AccountBook(User user) {
        this.user = user;
        this.contents = new ArrayList<>(30);
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            String action = getAction();
            execAction(action);
        }
    }

    public boolean of(String userName) {
        return user.correctName(userName);
    }

    private String getAction() {
        return Input.getString("press (1: 입력, 2: 삭제, 3: 수정, 4: 출력, 0: 종료) + enter >>>>> ");
    }

    private void execAction(String action) {
        switch (action) {
            case "1":
                createContent();
                break;
            case "2":
                deleteContent(Input.getInteger("삭제할 데이터의 순번을 입력해주세요 >>>>> "));
                break;
            case "3":
                updateContent(Input.getInteger("수정할 데이터의 순번을 입력해주세요 >>>>> "));
                break;
            case "4":
                printContents(Input.getInteger("출력할 대상 월을 입력해주세요(0: 전체) >>>>> "));
                break;
            case "0":
                isRunning = false;
                break;
            default:
                break;
        }
    }

    private void createContent() {
        String date = Input.getYYYYMMDD();
        String summary = Input.getString("적요 >>>>> ");
        int income = Input.getInteger("수입 >>>>> ");
        int expense = Input.getInteger("지출 >>>>> ");
        PaymentType paymentType = Input.getPaymentType();

        contents.add(new AccountData(generateNo++, date, summary, income, expense, paymentType));
    }

    private AccountData findOne(int no) {
        for (AccountData content : contents) {
            if (content.no == no) {
                return content;
            }
        }
        return null;
    }

    private void deleteContent(int no) {
        AccountData target = findOne(no);
        if (target == null) {
            System.out.println("해당 순번의 데이터가 존재하지 않습니다.");
            return;
        }
        String result = contents.remove(target) ? "delete success" : "delete fail";
        System.out.println(result);
    }

    private void updateContent(int no) {
        AccountData target = findOne(no);
        if (target == null) {
            System.out.println("해당 순번의 데이터가 존재하지 않습니다.");
            return;
        }

        int targetIndex = contents.indexOf(target);
        target.yyyymmdd = Input.getYYYYMMDD();
        target.summary = Input.getString("적요 >>>>> ");
        target.income = Input.getInteger("수입 >>>>> ");
        target.expense = Input.getInteger("지출 >>>>> ");
        target.paymentType = Input.getPaymentType();
        AccountData result = contents.set(targetIndex, target);
        System.out.printf("update success : [%d] %s %s %d %d %s %n"
            , result.no, result.yyyymmdd, result.summary, result.income, result.expense,
            result.paymentType);
    }

    private int calcMonthlyBalance(int month) {
        return contents.stream()
            .filter(
                content -> Integer.parseInt(content.yyyymmdd.substring(4, 6)) == (month == 0 ?
                    Integer.parseInt(content.yyyymmdd.substring(4, 6)) : month))
            .mapToInt(content -> content.income - content.expense)
            .sum();
    }

    private int calcTotalBalance() {
        return contents.stream()
            .mapToInt(content -> content.income - content.expense)
            .sum();
    }

    private void printContents(int month) {
        System.out.println("========== " + (month == 0 ? "전체" : month) + " 월의 지출내역 출력 ==========");
        System.out.printf("[순번] 날짜 적요 수입 지출 결제유형 %n");
        contents.stream()
            .filter(
                content -> Integer.parseInt(content.yyyymmdd.substring(4, 6)) == (month == 0 ?
                    Integer.parseInt(content.yyyymmdd.substring(4, 6)) : month))
            .forEach(content -> System.out.printf("[%d] %s %s %d %d %s %n"
                , content.no, content.yyyymmdd, content.summary, content.income, content.expense,
                content.paymentType));
        System.out.println("=======================================");
        System.out
            .println("잔액 : " + calcMonthlyBalance(month) + "원, 총잔액 : " + calcTotalBalance() + "원");
    }
}
