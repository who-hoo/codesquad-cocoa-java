package day06.mission3.accountbook;

public class AccountData {

    int no;
    String yyyymmdd;
    String summary;
    int income;
    int expense;

    AccountData(int no, String date, String summary, int income, int expense) {
        this.no = no;
        this.yyyymmdd = date;
        this.summary = summary;
        this.income = income;
        this.expense = expense;
    }
}
