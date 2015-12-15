package ru.unn.agile.MarksAccounting.model;


public class NoMarkCorrectionException extends RuntimeException {
    public NoMarkCorrectionException(final String message) {
        super(message);
    }
}
