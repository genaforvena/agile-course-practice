package ru.unn.agile.MarksAccounting.model;


public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(final String message) {
        super(message);
    }
}
