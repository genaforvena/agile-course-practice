package ru.unn.agile.Complex.viewmodel;

public enum Operation {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String name;

    private Operation(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
