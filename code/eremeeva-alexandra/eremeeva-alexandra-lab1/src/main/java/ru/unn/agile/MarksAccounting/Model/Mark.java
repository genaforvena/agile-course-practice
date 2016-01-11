package ru.unn.agile.MarksAccounting.Model;

import java.util.GregorianCalendar;

public class Mark {

    private int value;
    private final String academicSubject;
    private final GregorianCalendar date;

    public Mark(final int currentValue, final String currentAcademicSubject,
                final GregorianCalendar currentDate) {
        if (currentValue > 0) {
            this.value = currentValue;
            this.academicSubject = currentAcademicSubject;
            this.date = currentDate;
        } else {
            throw new MarkIsNotPositiveException("Mark must be positive");
        }
    }

    public int getValue() {
        return value;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getAcademicSubject() {
        return academicSubject;
    }

    @Override
    public int hashCode() {
        final int temp = 100;
        return temp * temp * value
                + temp * academicSubject.hashCode() + date.hashCode();
    }

    @Override
    public boolean equals(final Object comparedMark) {
        Mark temp = (Mark) comparedMark;
        return date.equals(temp.getDate())
                && academicSubject.equals(temp.getAcademicSubject())
                && value == temp.getValue();
    }

    public boolean isMarkCorrection(final Mark comparedMark) {
        return getDate().equals(comparedMark.getDate())
                && getAcademicSubject().equals(comparedMark.getAcademicSubject());
    }
}
