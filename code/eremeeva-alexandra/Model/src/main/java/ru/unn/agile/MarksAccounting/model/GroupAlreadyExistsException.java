package ru.unn.agile.MarksAccounting.model;


public class GroupAlreadyExistsException extends RuntimeException {
    public GroupAlreadyExistsException(final String message) {
        super(message);
    }
}
