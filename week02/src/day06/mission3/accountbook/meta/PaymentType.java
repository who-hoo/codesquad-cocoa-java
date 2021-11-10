package day06.mission3.accountbook.meta;

import org.jetbrains.annotations.NotNull;

public enum PaymentType {
    ONLY_INCOME("0"),
    CASH("1"),
    CARD("2");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @NotNull
    public static PaymentType getMatchedPaymentType(String value) {
        for (PaymentType type : PaymentType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }
}
