package day06.mission3.accountbook;

public class AccountData {

    static int generateNo = 0;
    static int balance = 0;

    int no;
    String date;
    String summary;
    int income;
    int expense;

    AccountData(String date, String summary, int income, int expense) {
        generateNo++;
        AccountData.balance += (income - expense);
        this.no = AccountData.generateNo;
        this.date = date;
        this.summary = summary;
        this.income = income;
        this.expense = expense;
    }
}
