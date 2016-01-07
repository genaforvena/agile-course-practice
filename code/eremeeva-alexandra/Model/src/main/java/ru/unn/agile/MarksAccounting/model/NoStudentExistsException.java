package ru.unn.agile.MarksAccounting.model;

public class NoStudentExistsException extends RuntimeException {
    public NoStudentExistsException(final String message) {
        super(message);
    }
}
