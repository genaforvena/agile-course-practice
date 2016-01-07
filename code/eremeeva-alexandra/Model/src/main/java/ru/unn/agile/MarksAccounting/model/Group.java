package ru.unn.agile.MarksAccounting.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Group {
    private final String number;
    private final ArrayList<Student> students;
    private final ArrayList<String> academicSubjects;

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

    public Student[] getStudentsAsArray() {
        return getStudents().toArray(new Student[getStudents().size()]);
    }

    public String[] getStudentsAsArrayOfStrings() {
        Student[] students = getStudentsAsArray();
        String[] result = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            result[i] = students[i].getName();
        }
        return result;
    }

    public String[] getStudentsWhereMarksExistAsArrayOfStrings() {
        if (doMarksExist()) {
            ArrayList<String> studentsWhereMarksExist = new ArrayList<String>();
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).doMarksExist()) {
                    studentsWhereMarksExist.add(students.get(i).getName());
                }
            }
            return studentsWhereMarksExist.toArray(new String[studentsWhereMarksExist.size()]);
        }
        return new String[0];
    }

    public boolean doStudentsExist() {
        return !students.isEmpty();
    }

    public ArrayList<GregorianCalendar> getDates(final String requiredAcademicSubject) {
        findAcademicSubject(requiredAcademicSubject);
        Student[] students = getStudentsAsArray();
        if (students.length == 0) {
            throw new NoStudentExistsException("No student exists!");
        }
        ArrayList<GregorianCalendar> allDates = students[0].getDates(requiredAcademicSubject);
        int i = 1;
        while (allDates.isEmpty() && i < students.length) {
            allDates = students[i].getDates(requiredAcademicSubject);
            i++;
        }
        if (allDates.isEmpty()) {
            throw new NoMarkExistsException("No mark exists!");
        }
        ArrayList<GregorianCalendar> studentDates;
        for (int j = i; j < students.length; j++) {
            studentDates = students[j].getDates(requiredAcademicSubject);
            for (int k = 0; k < studentDates.size(); k++) {
                int l = 0;
                while (l < allDates.size() && allDates.get(l).before(studentDates.get(k))) {
                    l++;
                }
                if (l == allDates.size()) {
                    allDates.add(studentDates.get(k));
                } else {
                    if (!allDates.get(l).equals(studentDates.get(k))) {
                        allDates.add(l, studentDates.get(k));
                    }
                }
            }
        }
        return allDates;
    }

    private int findAcademicSubject(final String requiredAcademicSubject) {
        for (int i = 0; i < getAcademicSubjects().size(); i++) {
            if (getAcademicSubjects().get(i).equals(requiredAcademicSubject)) {
                return i;
            }
        }
        throw new AcademicSubjectDoesNotExistException(
                "Required academic subject doesn't exist");
    }

    public GregorianCalendar[] getDatesAsArray(final String requiredAcademicSubject) {
        ArrayList<GregorianCalendar> dates = getDates(requiredAcademicSubject);
        GregorianCalendar[] result = new GregorianCalendar[dates.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = dates.get(i);
        }
        return result;
    }

    public ArrayList<String> getAcademicSubjects() {
        return academicSubjects;
    }

    public String[] getAcademicSubjectsAsArray() {
        return getAcademicSubjects().toArray(new String[getAcademicSubjects().size()]);
    }

    public String[] getAcademicSubjectsWhereMarksExistAsArray(final Student requiredStudent) {
        if (doMarksExist()) {
            ArrayList<String> subjectsWhereMarksExist = new ArrayList<String>();
            for (int i = 0; i < academicSubjects.size(); i++) {
                Student student = students.get(findStudent(requiredStudent));
                if (student.doMarksInSubjectExist(academicSubjects.get(i))) {
                    subjectsWhereMarksExist.add(academicSubjects.get(i));
                }
            }
            return subjectsWhereMarksExist.toArray(new String[subjectsWhereMarksExist.size()]);
        }
        return new String[0];
    }

    public boolean doAcademicSubjectsExist() {
        return !academicSubjects.isEmpty();
    }

    public boolean doMarksExist() {
        if (doAcademicSubjectsExist() && doStudentsExist()) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).doMarksExist()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doMarksInSubjectExist(final String subject) {
        if (doStudentsExist()) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).doMarksInSubjectExist(subject)) {
                    return true;
                }
            }
        }
        return false;
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
            if (i == 0) {
                this.students.add(i, new Student(newStudent.getName()));
            } else {
                if (getStudents().get(i - 1).getName().equals(newStudent.getName())) {
                    throw new StudentAlreadyExistsException("This student already exists");
                } else {
                    this.students.add(i, new Student(newStudent.getName()));
                }
            }
        }
    }

    private int findStudent(final Student requiredStudent) {
        for (int i = 0; i < getStudents().size(); i++) {
            if (getStudents().get(i).getName().equals(requiredStudent.getName())) {
                return i;
            }
        }
        throw new StudentDoesNotExistException("Required student doesn't exist");
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
            if (i == 0) {
                this.academicSubjects.add(i, newAcademicSubject);
            } else {
                if (getAcademicSubjects().get(i - 1).equals(newAcademicSubject)) {
                    throw new SubjectAlreadyExistsException("This subject already exists");
                } else {
                    this.academicSubjects.add(i, newAcademicSubject);
                }
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
