package ru.unn.agile.MarksAccounting.Model;


public class AcademicSubjectDoesNotExistException extends RuntimeException {
    public AcademicSubjectDoesNotExistException(final String message) {
        super(message);
    }
}
