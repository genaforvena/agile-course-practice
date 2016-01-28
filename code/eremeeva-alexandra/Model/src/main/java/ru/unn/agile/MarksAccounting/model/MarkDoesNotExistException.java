package ru.unn.agile.MarksAccounting.model;


public class MarkDoesNotExistException extends RuntimeException {
    public MarkDoesNotExistException(final String message) {
        super(message);
    }
}
