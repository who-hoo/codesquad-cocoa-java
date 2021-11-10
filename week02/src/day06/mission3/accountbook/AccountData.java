package day06.mission3.accountbook;

import day06.mission3.accountbook.meta.PaymentType;

public class AccountData {

    final int no;
    String yyyymmdd;
    String summary;
    int income;
    int expense;
    PaymentType paymentType;

    AccountData(int no, String date, String summary, int income, int expense,
        PaymentType paymentType) {
        this.no = no;
        this.yyyymmdd = date;
        this.summary = summary;
        this.income = income;
        this.expense = expense;
        this.paymentType = paymentType;
    }

    String toFileFormatString(User user) {
        return user.getName() + " "
            + no + " "
            + yyyymmdd + " "
            + summary + " "
            + income + " "
            + expense + " "
            + paymentType + "\n";
    }
}
