package ru.unn.agile.MarksAccounting.Model;


public class StudentDoesNotExistException extends RuntimeException {
    public StudentDoesNotExistException(final String message) {
        super(message);
    }
}
