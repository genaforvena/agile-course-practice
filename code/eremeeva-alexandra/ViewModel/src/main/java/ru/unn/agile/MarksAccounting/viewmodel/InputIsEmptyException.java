package ru.unn.agile.MarksAccounting.viewmodel;

public class InputIsEmptyException extends RuntimeException {
    public InputIsEmptyException(final String message) {
        super(message);
    }
}
