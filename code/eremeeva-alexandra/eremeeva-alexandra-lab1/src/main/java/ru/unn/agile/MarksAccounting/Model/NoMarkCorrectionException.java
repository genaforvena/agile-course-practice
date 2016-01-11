package ru.unn.agile.MarksAccounting.Model;


public class NoMarkCorrectionException extends RuntimeException {
    public NoMarkCorrectionException(final String message) {
        super(message);
    }
}
