package ua.lviv.iot.docslab.enums;

public enum CarType {
    ECONOMY("ECONOMY"),
    STANDARD("STANDARD"),
    BUSINESS("BUSINESS");

    private final String name;

    CarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}