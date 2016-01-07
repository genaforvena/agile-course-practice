package ru.unn.agile.MarksAccounting.model;

public class NoMarkExistsException extends RuntimeException {
    public NoMarkExistsException(final String message) {
        super(message);
    }
}
