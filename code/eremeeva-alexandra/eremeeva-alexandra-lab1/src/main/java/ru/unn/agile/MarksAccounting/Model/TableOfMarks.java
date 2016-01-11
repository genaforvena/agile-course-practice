package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableOfMarks {

    private final ArrayList<Group> groups;

    private int findGroup(final Group requiredGroup) {
        for (int i = 0; i < getGroups().size(); i++) {
            if (getGroups().get(i).getNumber().equals(requiredGroup.getNumber())) {
                return i;
            }
        }
        throw new GroupDoesNotExistException("Required group doesn't exist");
    }

    public TableOfMarks() {
        groups = new ArrayList<Group>();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(final Group newGroup) {
        if (getGroups().isEmpty()) {
            groups.add(new Group(newGroup.getNumber()));
        } else {
            int i;
            for (i = 0; i < getGroups().size(); i++) {
                if (getGroups().get(i).getNumber().compareTo(newGroup.getNumber()) > 0) {
                    break;
                }
            }
            if (!getGroups().get(i - 1).getNumber().equals(newGroup.getNumber())) {
                groups.add(i, new Group(newGroup.getNumber()));
            }
        }
    }

    public void addStudent(final Group requiredGroup, final Student newStudent)
    {
        groups.get(findGroup(requiredGroup)).addStudent(newStudent);
    }

    public void addAcademicSubject(final Group requiredGroup, final String newAcademicSubject)
    {
        groups.get(findGroup(requiredGroup)).addAcademicSubject(newAcademicSubject);
    }

    public void addNewMark(final Mark newMark, final Student requiredStudent,
                           final Group requiredGroup) {
        groups.get(findGroup(requiredGroup)).addNewMark(newMark, requiredStudent);
    }

    @Override
    public int hashCode() {
        return groups.hashCode();
    }

    @Override
    public boolean equals(final Object comparedTableOfMarks) {
        TableOfMarks temp = (TableOfMarks) comparedTableOfMarks;
        return groups.equals(temp.getGroups());
    }

    public void deleteGroup(final Group requiredGroup) {
        groups.remove(findGroup(requiredGroup));
    }

    public Mark getMark(final Group requiredGroup, final Student requiredStudent,
                       final String requiredAcademicSubject, final GregorianCalendar requiredDate) {
        return getGroups().get(findGroup(requiredGroup)).getMark(requiredStudent,
                requiredAcademicSubject, requiredDate);
    }

    public void deleteMark(final Group requiredGroup, final Student requiredStudent,
                            final String requiredAcademicSubject,
                            final GregorianCalendar requiredDate) {
        groups.get(findGroup(requiredGroup)).deleteMark(requiredStudent,
                requiredAcademicSubject, requiredDate);
    }

    public void deleteAcademicSubject(final Group requiredGroup,
                                      final String requiredAcademicSubject) {
        groups.get(findGroup(requiredGroup)).deleteAcademicSubject(requiredAcademicSubject);
    }

    public void deleteStudent(final Group requiredGroup, final Student requiredStudent) {
        groups.get(findGroup(requiredGroup)).deleteStudent(requiredStudent);
    }
}
