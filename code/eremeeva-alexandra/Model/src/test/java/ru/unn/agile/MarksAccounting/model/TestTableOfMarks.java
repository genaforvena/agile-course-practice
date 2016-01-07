package ru.unn.agile.MarksAccounting.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTableOfMarks {
    private TableOfMarks tableOfMarks;
    private TableOfMarks comparedTableOfMarks;
    private ArrayList<Group> groups;

    @Before
    public void setUp() {
        initArrayListGroups();
        initGroups();
    }

    private void initArrayListGroups() {
        groups = new ArrayList<Group>();
        groups.add(new Group("1"));
        groups.get(0).addStudent(new Student("Ivanov"));
        groups.get(0).addStudent(new Student("Sidorov"));
        groups.get(0).addAcademicSubject("History");
        groups.get(0).addAcademicSubject("Maths");
    }

    private void initGroups() {
        tableOfMarks = new TableOfMarks();
        comparedTableOfMarks = new TableOfMarks();
        tableOfMarks.addGroup(new Group("1"));
        comparedTableOfMarks.addGroup(new Group("1"));
        comparedTableOfMarks.addGroup(new Group("2"));
        tableOfMarks.addAcademicSubject(new Group("1"), "History");
        tableOfMarks.addAcademicSubject(new Group("1"), "Maths");
        comparedTableOfMarks.addAcademicSubject(new Group("1"), "History");
        comparedTableOfMarks.addAcademicSubject(new Group("1"), "Maths");
        comparedTableOfMarks.addAcademicSubject(new Group("2"), "Science");
        tableOfMarks.addStudent(new Group("1"), new Student("Ivanov"));
        tableOfMarks.addStudent(new Group("1"), new Student("Sidorov"));
        comparedTableOfMarks.addStudent(new Group("1"), new Student("Ivanov"));
        comparedTableOfMarks.addStudent(new Group("1"), new Student("Sidorov"));
        comparedTableOfMarks.addStudent(new Group("2"), new Student("Petrov"));
        comparedTableOfMarks.addNewMark(
                new Mark(4, "Science", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Petrov"), new Group("2"));
    }

    @Test
    public void canGetGroups() {
        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test
    public void canGetGroupsAsArrayOfStrings() {
        String[] groupsArray = {"1", "2"};

        assertArrayEquals(groupsArray, comparedTableOfMarks.getGroupsAsArrayOfStrings());
    }

    @Test
    public void canGetGroupsWhereStudentsExistAsArrayOfStrings() {
        String[] groupsWhereStudentsExistArray = {"1"};

        tableOfMarks.addGroup(new Group("2"));

        assertArrayEquals(groupsWhereStudentsExistArray,
                tableOfMarks.getGroupsWhereStudentsExistAsArrayOfStrings());
    }

    @Test
    public void canGetGroupsWhereSubjectsExistAsArrayOfStrings() {
        String[] groupWhereSubjectsExistArray = {"1"};

        tableOfMarks.addGroup(new Group("2"));

        assertArrayEquals(groupWhereSubjectsExistArray,
                tableOfMarks.getGroupsWhereSubjectsExistAsArrayOfStrings());
    }

    @Test
    public void getGroupsWhereCanAddMarksAsArrayOfStrings() {
        String[] groupsWhereCanAddMarksArray = {"1"};

        tableOfMarks.addGroup(new Group("2"));

        assertArrayEquals(groupsWhereCanAddMarksArray,
                tableOfMarks.getGroupsWhereCanAddMarksAsArrayOfStrings());
    }

    @Test
    public void canGetGroupsWhereMarksExistAsArrayOfStrings() {
        String[] groupsWhereMarksExistArray = {"2"};

        assertArrayEquals(groupsWhereMarksExistArray,
                comparedTableOfMarks.getGroupsWhereMarksExistAsArrayOfStrings());
    }

    @Test
    public void canIndicateWhenGroupsExist() {
        assertTrue(tableOfMarks.doGroupsExist());
    }

    @Test
    public void canIndicateWhenGroupsDoNotExist() {
        TableOfMarks emptyTableOfMarks = new TableOfMarks();

        assertFalse(emptyTableOfMarks.doGroupsExist());
    }

    @Test
    public void canGetStudents() {
        assertEquals(groups.get(0).getStudents(),
                tableOfMarks.getStudents(new Group("1")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetStudentsWhenGroupDoesNotExist() {
        tableOfMarks.getStudents(new Group("116"));
    }

    @Test
    public void canGetStudentsAsArray() {
        Student[] studentsArray = {new Student("Ivanov"), new Student("Sidorov")};

        assertArrayEquals(studentsArray, tableOfMarks.getStudentsAsArray(new Group("1")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetStudentsAsArrayWhenGroupDoesNotExist() {
        tableOfMarks.getStudentsAsArray(new Group("116"));
    }

    @Test
    public void canGetStudentsAsArrayOfStrings() {
        String[] studentsArray = {"Ivanov", "Sidorov"};

        assertArrayEquals(studentsArray, tableOfMarks.getStudentsAsArrayOfStrings(new Group("1")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetStudentsAsArrayOfStringsWhenGroupDoesNotExist() {
        tableOfMarks.getStudentsAsArrayOfStrings(new Group("116"));
    }

    @Test
    public void canGetStudentsWhereMarksExistAsArrayOfStrings() {
        String[] studentsWhereMarksExistArray = {"Petrov"};

        assertArrayEquals(studentsWhereMarksExistArray,
                comparedTableOfMarks.getStudentsWhereMarksExistAsArrayOfStrings(new Group("2")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetStudentsWhereMarksExistAsArrayOfStrings() {
        tableOfMarks.getStudentsWhereMarksExistAsArrayOfStrings(new Group("116"));
    }

    @Test
    public void canIndicateWhenStudentsExist() {
        assertTrue(tableOfMarks.doStudentsExist());
    }

    @Test
    public void canIndicateWhenStudentsDoNotExist() {
        TableOfMarks tableOfMarksWithoutStudents = new TableOfMarks();
        tableOfMarksWithoutStudents.addGroup(new Group("1"));
        tableOfMarksWithoutStudents.addGroup(new Group("2"));
        tableOfMarksWithoutStudents.addAcademicSubject(new Group("1"), "Maths");

        assertFalse(tableOfMarksWithoutStudents.doStudentsExist());
    }

    @Test
    public void canGetAcademicSubjects() {
        assertEquals(groups.get(0).getAcademicSubjects(),
                tableOfMarks.getAcademicSubjects(new Group("1")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetAcademicSubjectsWhenGroupDoesNotExist() {
        tableOfMarks.getAcademicSubjects(new Group("116"));
    }

    @Test
    public void canGetAcademicSubjectsAsArray() {
        String[] subjectsArray = {"History", "Maths"};

        assertArrayEquals(subjectsArray, tableOfMarks.getAcademicSubjectsAsArray(new Group("1")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetAcademicSubjectsAsArrayWhenGroupDoesNotExist() {
        tableOfMarks.getAcademicSubjectsAsArray(new Group("116"));
    }

    @Test
    public void canGetAcademicSubjectsWhereMarksExistAsArray() {
        String[] subjectsWhereMarksExistArray = {"Science"};

        assertArrayEquals(subjectsWhereMarksExistArray,
                comparedTableOfMarks.getAcademicSubjectsWhereMarksExistAsArray(new Group("2"),
                        new Student("Petrov")));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetAcademicSubjectsWhereMarksExistAsArrayWhenGroupDoesNotExist() {
        comparedTableOfMarks.getAcademicSubjectsWhereMarksExistAsArray(new Group("116"),
                new Student("Petrov"));
    }

    @Test
    public void canIndicateWhenAcademicSubjectsExist() {
        assertTrue(tableOfMarks.doAcademicSubjectsExist());
    }

    @Test
    public void canIndicateWhenAcademicSubjectsDoNotExist() {
        TableOfMarks tableOfMarksWithoutSubjects = new TableOfMarks();
        tableOfMarksWithoutSubjects.addGroup(new Group("1"));
        tableOfMarksWithoutSubjects.addGroup(new Group("2"));
        tableOfMarksWithoutSubjects.addStudent(new Group("1"), new Student("Petrov"));

        assertFalse(tableOfMarksWithoutSubjects.doAcademicSubjectsExist());
    }

    @Test
    public void canGetDates() {
        ArrayList<GregorianCalendar> dates = new ArrayList<GregorianCalendar>();
        dates.add(new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));

        assertEquals(dates, comparedTableOfMarks.getDates(new Group("2"), "Science"));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetDatesWhenGroupDoesNotExist() {
        comparedTableOfMarks.getDates(new Group("116"), "Maths");
    }

    @Test
    public void canIndicateWhenMarksExist() {
        assertTrue(comparedTableOfMarks.doMarksExist());
    }

    @Test
    public void canIndicateWhenMarksDoNotExist() {
        assertFalse(tableOfMarks.doMarksExist());
    }

    @Test
    public void canGetDatesAsArray() {
        GregorianCalendar[] dates = {new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)};

        assertArrayEquals(dates, comparedTableOfMarks.getDatesAsArray(new Group("2"), "Science"));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetDatesAsArrayWhenGroupDoesNotExist() {
        comparedTableOfMarks.getDatesAsArray(new Group("116"), "Maths");
    }

    @Test
    public void canIndicateWhenCanAddMark() {
        assertTrue(tableOfMarks.canAddMark());
    }

    @Test
    public void canIndicateWhenCanNotAddMark() {
        TableOfMarks tableOfMarks = new TableOfMarks();
        tableOfMarks.addGroup(new Group("1"));
        tableOfMarks.addGroup(new Group("2"));
        tableOfMarks.addStudent(new Group("1"), new Student("Petrov"));
        tableOfMarks.addAcademicSubject(new Group("2"), "Maths");

        assertFalse(tableOfMarks.canAddMark());
    }

    @Test
    public void canIndicateWhenMarksInRequiredSubjectAndGroupExist() {
        assertTrue(comparedTableOfMarks.doMarksInSubjectExist(new Group("2"), "Science"));
    }

    @Test
    public void canIndicateWhenMarksInRequiresSubjectAndGroupDoNotExist() {
        assertFalse(tableOfMarks.doMarksInSubjectExist(new Group("1"), "History"));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotIndicateWhetherMarksExistWhenRequiredGroupDoesNotExist() {
        comparedTableOfMarks.doMarksInSubjectExist(new Group("116"), "Science");
    }

    @Test
    public void canAddGroup() {
        groups.add(new Group("2"));

        tableOfMarks.addGroup(new Group("2"));

        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupAlreadyExistsException.class)
    public void canNotAddGroupWhenGroupAlreadyExists() {
        tableOfMarks.addGroup(new Group("1"));
    }

    @Test
    public void canAddStudent() {
        groups.get(0).addStudent(new Student("Kornyakov"));

        tableOfMarks.addStudent(new Group("1"), new Student("Kornyakov"));

        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotAddStudentWhenGroupDoesNotExist() {
        tableOfMarks.addStudent(new Group("2"), new Student("Kornyakov"));
    }

    @Test
    public void canAddSubject() {
        groups.get(0).addAcademicSubject("Science");

        tableOfMarks.addAcademicSubject(new Group("1"), "Science");

        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void  canNotAddAcademicSubjectWhenGroupDoesNotExist() {
        tableOfMarks.addAcademicSubject(new Group("2"), "Maths");
    }

    @Test
    public void canAddNewMark() {
        groups.get(0).addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"));

        tableOfMarks.addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"), new Group("1"));

        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotAddMarkWhenGroupDoesNotExist() {
        tableOfMarks.addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"), new Group("2"));
    }

    @Test
    public void whenEquals() {
        TableOfMarks equivalentTableOfMarks = tableOfMarks;
        equivalentTableOfMarks.addGroup(new Group("2"));
        equivalentTableOfMarks.addAcademicSubject(new Group("2"), "Science");
        equivalentTableOfMarks.addStudent(new Group("2"), new Student("Petrov"));
        equivalentTableOfMarks.addNewMark(
                new Mark(4, "Science", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Petrov"), new Group("2"));

        assertTrue(equivalentTableOfMarks.equals(comparedTableOfMarks));
    }

    @Test
    public void whenDoesNotEqual() {
        assertFalse(tableOfMarks.equals(comparedTableOfMarks));
    }

    @Test
    public void canGetMark() {
        Mark four = new Mark(4, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));

        assertEquals(four, comparedTableOfMarks.getMark(new Group("2"), new Student("Petrov"),
                "Science", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotGetMarkWhenGroupDoesNotExist() {
        tableOfMarks.getMark(new Group("2"), new Student("Sidorov"),  "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteMark() {
        tableOfMarks.addGroup(new Group("2"));
        tableOfMarks.addStudent(new Group("2"), new Student("Petrov"));
        tableOfMarks.addAcademicSubject(new Group("2"), "Science");

        comparedTableOfMarks.deleteMark(new Group("2"), new Student("Petrov"),  "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));

        assertEquals(tableOfMarks, comparedTableOfMarks);
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDDeleteMarkWhenGroupDoesNotExist() {
        tableOfMarks.deleteMark(new Group("2"), new Student("Petrov"),  "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteGroup() {
        comparedTableOfMarks.deleteGroup(new Group("2"));

        assertEquals(tableOfMarks, comparedTableOfMarks);
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDeleteGroupWhenGroupDoesNotExist() {
        tableOfMarks.deleteGroup(new Group("2"));
    }

    @Test
    public void canDeleteStudent() {
        groups.get(0).deleteStudent(new Student("Sidorov"));

        tableOfMarks.deleteStudent(new Group("1"), new Student("Sidorov"));

        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDeleteStudentWhenGroupDoesNotExist() {
        tableOfMarks.deleteStudent(new Group("2"), new Student("Sidorov"));
    }

    @Test
    public void canDeleteAcademicSubject() {
        groups.get(0).deleteAcademicSubject("History");

        tableOfMarks.deleteAcademicSubject(new Group("1"), "History");

        assertEquals(groups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDeleteAcademicSubjectWhenGroupDoesNotExist() {
        tableOfMarks.deleteAcademicSubject(new Group("2"), "History");
    }

}
