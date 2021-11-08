package day06.mission3.accountbook;

import java.util.ArrayList;

public class AccountBook {

    Input input = new Input();
    User user;
    ArrayList<AccountData> contents;
    boolean isRunning = true;

    AccountBook(User user) {
        this.user = user;
        this.contents = new ArrayList<>(30);
        this.contents.add(new AccountData("20211108", "test1", 100, 0));
        this.contents.add(new AccountData("20211108", "test2", 200, 0));
        this.contents.add(new AccountData("20211108", "test3", 0, 100));
    }

    void run() {
        while (isRunning) {
            String action = getAction();
            execAction(action);
        }
    }

    String getAction() {
        return input.getString("press (1: 입력, 2: 삭제, 3: 수정, 4: 출력, 0: 종료) + enter >>>>> ");
    }

    void execAction(String action) {
        switch (action) {
            case "1":
                createContent();
                break;
            case "2":
                deleteContent(input.getInteger("삭제할 데이터의 순번을 입력해주세요 >>>>> "));
                break;
            case "3":
                updateContent(input.getInteger("수정할 데이터의 순번을 입력해주세요 >>>>> "));
                break;
            case "4":
                printContents(input.getInteger("출력할 대상 월을 입력해주세요(0: 전체) >>>>> "));
                break;
            case "0":
                isRunning = false;
                break;
            default:
                break;
        }
    }

    void createContent() {
        String date = input.getYYYYMMDD();
        String summary = input.getString("적요 >>>>> ");
        int income = input.getInteger("수입 >>>>> ");
        int expense = input.getInteger("지출 >>>>> ");

        contents.add(new AccountData(date, summary, income, expense));
    }

    AccountData findOne(int no) {
        for (AccountData content : contents) {
            if (content.no == no) {
                return content;
            }
        }
        return null;
    }

    void deleteContent(int no) {
        AccountData target = findOne(no);
        if (target == null) {
            System.out.println("해당 순번의 데이터가 존재하지 않습니다.");
            return;
        }
        AccountData.subBalance(target.income, target.expense);
        String result = contents.remove(target) ? "delete success" : "delete fail";
        System.out.println(result);
    }

    void updateContent(int no) {
        AccountData target = findOne(no);
        if (target == null) {
            System.out.println("해당 순번의 데이터가 존재하지 않습니다.");
            return;
        }
        AccountData.subBalance(target.income, target.expense);

        int targetIndex = contents.indexOf(target);
        target.date = input.getYYYYMMDD();
        target.summary = input.getString("적요 >>>>> ");
        target.income = input.getInteger("수입 >>>>> ");
        target.expense = input.getInteger("지출 >>>>> ");
        AccountData result = contents.set(targetIndex, target);
        AccountData.addBalance(target.income, target.expense);
        System.out.printf("update success : [%d] %s %s %d %d %n"
            , result.no, result.date, result.summary, result.income, result.expense);
    }

    int calcMonthlyBalance(int month) {
        return contents.stream()
            .filter(
                content -> Integer.parseInt(content.date.substring(4, 6)) == (month == 0 ?
                    Integer.parseInt(content.date.substring(4, 6)) : month))
            .mapToInt(content -> content.income - content.expense).sum();
    }

    void printContents(int month) {
        System.out.println("========== " + (month == 0 ? "전체" : month) + " 월의 지출내역 출력 ==========");
        System.out.printf("[순번] 날짜 적요 수입 지출 %n");
        contents.stream()
            .filter(
                content -> Integer.parseInt(content.date.substring(4, 6)) == (month == 0 ?
                    Integer.parseInt(content.date.substring(4, 6)) : month))
            .forEach(content -> System.out.printf("[%d] %s %s %d %d %n"
                , content.no, content.date, content.summary, content.income, content.expense));
        System.out.println("=======================================");
        System.out.println("잔액 : " + calcMonthlyBalance(month) + "원, 총잔액 : " + AccountData.balance + "원");
    }
}
