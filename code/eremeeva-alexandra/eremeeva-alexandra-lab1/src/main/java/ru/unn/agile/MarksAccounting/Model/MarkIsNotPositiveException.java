package ru.unn.agile.MarksAccounting.Model;


public class MarkIsNotPositiveException extends RuntimeException {
    public MarkIsNotPositiveException(final String message) {
        super(message);
    }
}
