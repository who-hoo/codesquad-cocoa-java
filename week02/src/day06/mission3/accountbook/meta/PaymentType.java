package day06.mission3.accountbook.meta;

public enum PaymentType {
    CASH("1"),
    CARD("2");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
