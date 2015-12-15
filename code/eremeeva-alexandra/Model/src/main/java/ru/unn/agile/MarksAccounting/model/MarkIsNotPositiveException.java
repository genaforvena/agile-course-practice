package ru.unn.agile.MarksAccounting.model;


public class MarkIsNotPositiveException extends RuntimeException {
    public MarkIsNotPositiveException(final String message) {
        super(message);
    }
}
