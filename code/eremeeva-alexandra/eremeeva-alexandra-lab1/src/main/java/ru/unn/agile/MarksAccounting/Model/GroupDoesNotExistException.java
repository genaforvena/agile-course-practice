package ru.unn.agile.MarksAccounting.Model;


public class GroupDoesNotExistException extends RuntimeException {
    public GroupDoesNotExistException(final String message) {
        super(message);
    }
}
