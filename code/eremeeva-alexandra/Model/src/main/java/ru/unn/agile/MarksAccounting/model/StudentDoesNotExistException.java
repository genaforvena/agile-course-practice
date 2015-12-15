package ru.unn.agile.MarksAccounting.model;


public class StudentDoesNotExistException extends RuntimeException {
    public StudentDoesNotExistException(final String message) {
        super(message);
    }
}
