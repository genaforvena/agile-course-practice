package ru.unn.agile.MarksAccounting.model;


public class GroupDoesNotExistException extends RuntimeException {
    public GroupDoesNotExistException(final String message) {
        super(message);
    }
}
