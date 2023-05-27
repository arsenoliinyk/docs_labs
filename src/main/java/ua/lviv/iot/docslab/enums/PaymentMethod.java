package ua.lviv.iot.docslab.enums;

public enum PaymentMethod {
    CARD("CARD"),
    CASH("CASH");

    private final String name;

    PaymentMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}