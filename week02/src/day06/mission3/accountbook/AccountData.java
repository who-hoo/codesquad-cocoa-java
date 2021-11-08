package day06.mission3.accountbook;

public class AccountData {

    static int generateNo = 0;

    int no;
    String date;
    String summary;
    int income;
    int expense;

    AccountData(int no, String date, String summary, int income, int expense) {
        this.no = no;
        this.no = AccountData.generateNo;
        this.date = date;
        this.summary = summary;
        this.income = income;
        this.expense = expense;
    }
}
