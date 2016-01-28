package ru.unn.agile.MarksAccounting.model;


public class AcademicSubjectDoesNotExistException extends RuntimeException {
    public AcademicSubjectDoesNotExistException(final String message) {
        super(message);
    }
}
