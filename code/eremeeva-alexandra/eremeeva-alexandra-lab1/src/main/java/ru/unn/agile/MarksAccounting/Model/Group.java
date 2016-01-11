package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Group {

    private final String number;
    private final ArrayList<Student> students;
    private final ArrayList<String> academicSubjects;

    private int findAcademicSubject(final String requiredAcademicSubject) {
        for (int i = 0; i < getAcademicSubjects().size(); i++) {
            if (getAcademicSubjects().get(i).equals(requiredAcademicSubject)) {
                return i;
            }
        }
        throw new AcademicSubjectDoesNotExistException(
                "Required academic subject doesn't exist");
    }

    private int findStudent(final Student requiredStudent) {
        for (int i = 0; i < getStudents().size(); i++) {
            if (getStudents().get(i).getName().equals(requiredStudent.getName())) {
                return i;
            }
        }
        throw new StudentDoesNotExistException("Required student doesn't exist");
    }

    public Group(final String currentNumber) {
        this.number = currentNumber;
        this.students = new ArrayList<Student>();
        this.academicSubjects = new ArrayList<String>();
    }

    public String getNumber() {
        return number;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<String> getAcademicSubjects() {
        return academicSubjects;
    }

    public void addStudent(final Student newStudent) {
        int i;
        if (getStudents().isEmpty()) {
            this.students.add(new Student(newStudent.getName()));
        } else {
            for (i = 0; i < getStudents().size(); i++) {
                if (getStudents().get(i).getName().compareTo(newStudent.getName()) > 0) {
                    break;
                }
            }
            if (!getStudents().get(i - 1).getName().equals(newStudent.getName())) {
                this.students.add(i, new Student(newStudent.getName()));
            }
        }
    }

    public void addAcademicSubject(final String newAcademicSubject) {
        int i;
        if (getAcademicSubjects().isEmpty()) {
            this.academicSubjects.add(newAcademicSubject);
        } else {
            for (i = 0; i < getAcademicSubjects().size(); i++) {
                if (getAcademicSubjects().get(i).compareTo(newAcademicSubject) > 0) {
                    break;
                }
            }
            if (!getAcademicSubjects().get(i - 1).equals(newAcademicSubject)) {
                this.academicSubjects.add(i, newAcademicSubject);
            }
        }
    }

    public int hashCode() {
        final int temp = 10;
        return temp * students.hashCode() + temp * temp * academicSubjects.hashCode()
                + number.hashCode();
    }

    @Override
    public boolean equals(final Object comparedGroup) {
        Group temp = (Group) comparedGroup;
        return temp.getNumber().equals(number)
                && temp.getStudents().equals(students)
                && temp.getAcademicSubjects().equals(academicSubjects);
    }

    public void addNewMark(final Mark newMark, final Student requiredStudent) {
        findAcademicSubject(newMark.getAcademicSubject());
        students.get(findStudent(requiredStudent)).addMark(newMark);
    }

    public Mark getMark(final Student requiredStudent, final String requiredAcademicSubject,
                       final GregorianCalendar requiredDate) {
        findAcademicSubject(requiredAcademicSubject);
        return students.get(findStudent(requiredStudent)).getMark(requiredAcademicSubject,
                requiredDate);
    }

    public void deleteMark(final Student requiredStudent, final String requiredAcademicSubject,
                           final GregorianCalendar requiredDate) {
        findAcademicSubject(requiredAcademicSubject);
        students.get(findStudent(requiredStudent)).deleteMark(
                requiredAcademicSubject, requiredDate);
    }

    public void deleteStudent(final Student requiredStudent) {
        students.remove(findStudent(requiredStudent));
    }

    public void deleteAcademicSubject(final String requiredAcademicSubject) {
        getAcademicSubjects().remove(findAcademicSubject(requiredAcademicSubject));
        for (int i = 0; i < getStudents().size(); i++) {
            students.get(i).deleteMarks(requiredAcademicSubject);
        }
    }
}
