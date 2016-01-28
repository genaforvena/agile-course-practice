package ru.unn.agile.MarksAccounting.model;


public class SubjectAlreadyExistsException extends RuntimeException {
    public SubjectAlreadyExistsException(final String message) {
        super(message);
    }
}
