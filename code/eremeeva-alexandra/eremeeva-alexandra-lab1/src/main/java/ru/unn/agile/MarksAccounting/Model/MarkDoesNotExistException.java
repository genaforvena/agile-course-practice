package ru.unn.agile.MarksAccounting.Model;


public class MarkDoesNotExistException extends RuntimeException {
    public MarkDoesNotExistException(final String message) {
        super(message);
    }
}
