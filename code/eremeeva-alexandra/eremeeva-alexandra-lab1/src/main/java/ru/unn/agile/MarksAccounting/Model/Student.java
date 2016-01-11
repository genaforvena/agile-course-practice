package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Student {

    private final String name;
    private final ArrayList<Mark> marks;

    private int findMark(final String requiredAcademicSubject,
                         final GregorianCalendar requiredDate) {
        for (int i = 0; i < getMarks().size(); i++) {
            if (getMarks().get(i).getDate().equals(requiredDate)
                    && getMarks().get(i).getAcademicSubject().equals(requiredAcademicSubject)) {
                return i;
            }
        }
        throw new MarkDoesNotExistException("Required mark doesn't exist");
    }

    public Student(final String currentName) {
        this.name = currentName;
        this.marks = new ArrayList<Mark>();
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int temp = 100;
        return temp * name.hashCode() + marks.hashCode();
    }

    @Override
    public boolean equals(final Object comparedStudent) {
        Student temp = (Student) comparedStudent;
        return name.equals(temp.getName()) && marks.equals(temp.getMarks());
    }

    public void addMark(final Mark newMark) {
        if (getMarks().isEmpty()) {
            marks.add(newMark);
        } else {
            int i;
            for (i = 0; i < getMarks().size(); i++) {
                if (newMark.isMarkCorrection(getMarks().get(i))) {
                    throw new NoMarkCorrectionException("This mark already exists, can't correct");
                }
                if (getMarks().get(i).getDate().after(newMark.getDate())) {
                    break;
                }
            }
            marks.add(i, newMark);
        }
    }

    public Mark getMark(final String requiredAcademicSubject,
                        final GregorianCalendar requiredDate) {
        return getMarks().get(findMark(requiredAcademicSubject, requiredDate));
    }

    public void deleteMark(final String requiredAcademicSubject,
                           final GregorianCalendar requiredDate) {
        marks.remove(findMark(requiredAcademicSubject, requiredDate));
    }

    public void deleteMarks(final String requieredAcademicSubject) {
        int i = 0;
        while (i < getMarks().size()) {
            if (getMarks().get(i).getAcademicSubject().equals(requieredAcademicSubject)) {
                marks.remove(i);
            } else {
                i++;
            }
        }
    }
}
