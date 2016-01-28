package ru.unn.agile.MarksAccounting.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableOfMarks {

    private final ArrayList<Group> groups;

    public TableOfMarks() {
        groups = new ArrayList<Group>();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String[] getGroupsAsArrayOfStrings() {
        String[] result = new String[getGroups().size()];
        for (int i = 0; i < getGroups().size(); i++) {
            result[i] = getGroups().get(i).getNumber();
        }
        return result;
    }

    public String[] getGroupsWhereStudentsExistAsArrayOfStrings() {
        if (doStudentsExist()) {
            ArrayList<String> groupsWithStudents = new ArrayList<String>();
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doStudentsExist()) {
                    groupsWithStudents.add(groups.get(i).getNumber());
                }
            }
            return groupsWithStudents.toArray(new String[groupsWithStudents.size()]);
        }
        return new String[0];
    }

    public String[] getGroupsWhereSubjectsExistAsArrayOfStrings() {
        if (doAcademicSubjectsExist()) {
            ArrayList<String> groupsWithSubjects = new ArrayList<String>();
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doAcademicSubjectsExist()) {
                    groupsWithSubjects.add(groups.get(i).getNumber());
                }
            }
            return groupsWithSubjects.toArray(new String[groupsWithSubjects.size()]);
        }
        return new String[0];
    }

    public String[] getGroupsWhereCanAddMarksAsArrayOfStrings() {
        if (doAcademicSubjectsExist() && doStudentsExist()) {
            ArrayList<String> groupsWhereCanAddMarks = new ArrayList<String>();
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doAcademicSubjectsExist()
                        && groups.get(i).doStudentsExist()) {
                    groupsWhereCanAddMarks.add(groups.get(i).getNumber());
                }
            }
            return groupsWhereCanAddMarks.toArray(new String[groupsWhereCanAddMarks.size()]);
        }
        return new String[0];
    }

    public String[] getGroupsWhereMarksExistAsArrayOfStrings() {
        if (doMarksExist()) {
            ArrayList<String> groupsWhereMarksExist = new ArrayList<String>();
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doMarksExist()) {
                    groupsWhereMarksExist.add(groups.get(i).getNumber());
                }
            }
            return groupsWhereMarksExist.toArray(new String[groupsWhereMarksExist.size()]);
        }
        return new String[0];
    }

    public boolean doGroupsExist() {
        return !groups.isEmpty();
    }

    public ArrayList<Student> getStudents(final Group requiredGroup) {
        return groups.get(findGroup(requiredGroup)).getStudents();
    }

    private int findGroup(final Group requiredGroup) {
        for (int i = 0; i < getGroups().size(); i++) {
            if (getGroups().get(i).getNumber().equals(requiredGroup.getNumber())) {
                return i;
            }
        }
        throw new GroupDoesNotExistException("Required group doesn't exist");
    }

    public Student[] getStudentsAsArray(final Group requiredGroup) {
        return groups.get(findGroup(requiredGroup)).getStudentsAsArray();
    }

    public String[] getStudentsAsArrayOfStrings(final Group requiredGroup) {
        return groups.get(findGroup(requiredGroup)).getStudentsAsArrayOfStrings();
    }

    public boolean doStudentsExist() {
        if (doGroupsExist()) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doStudentsExist()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getStudentsWhereMarksExistAsArrayOfStrings(final Group requiredGroup) {
        return groups.get(findGroup(requiredGroup)).getStudentsWhereMarksExistAsArrayOfStrings();
    }

    public ArrayList<String> getAcademicSubjects(final Group requiredGroup) {
        return groups.get(findGroup(requiredGroup)).getAcademicSubjects();
    }

    public String[] getAcademicSubjectsAsArray(final Group requiredGroup) {
        return groups.get(findGroup(requiredGroup)).getAcademicSubjectsAsArray();
    }

    public String[] getAcademicSubjectsWhereMarksExistAsArray(final Group requiredGroup,
                                                              final Student requiredStudent) {
        Group group = groups.get(findGroup(requiredGroup));
        if (doMarksExist()) {
            return group.getAcademicSubjectsWhereMarksExistAsArray(requiredStudent);
        }
        return new String[0];
    }

    public boolean doAcademicSubjectsExist() {
        if (doGroupsExist()) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doAcademicSubjectsExist()) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<GregorianCalendar> getDates(final Group requiredGroup,
                                                 final String requiredAcademicSubject) {
        return groups.get(findGroup(requiredGroup)).getDates(requiredAcademicSubject);
    }

    public GregorianCalendar[] getDatesAsArray(final Group requiredGroup,
                                                 final String requiredAcademicSubject) {
        return groups.get(findGroup(requiredGroup)).getDatesAsArray(requiredAcademicSubject);
    }

    public boolean canAddMark() {
        if (doGroupsExist()) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doStudentsExist() && groups.get(i).doAcademicSubjectsExist()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doMarksExist() {
        if (doStudentsExist() && doAcademicSubjectsExist()) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).doMarksExist()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doMarksInSubjectExist(final Group group, final String subject) {
        if (groups.get(findGroup(group)).doMarksInSubjectExist(subject)) {
            return true;
        }
        return false;
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
            if (i == 0) {
                groups.add(i, new Group(newGroup.getNumber()));
            } else {
                if (getGroups().get(i - 1).getNumber().equals(newGroup.getNumber())) {
                    throw new GroupAlreadyExistsException("This group already exists");
                } else {
                    groups.add(i, new Group(newGroup.getNumber()));
                }
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
