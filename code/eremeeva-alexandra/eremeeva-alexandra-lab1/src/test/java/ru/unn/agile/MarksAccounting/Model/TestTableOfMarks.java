package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTableOfMarks {

    private TableOfMarks tableOfMarks;
    private TableOfMarks comparedTableOfMarks;
    private ArrayList<Group> tempGroups;

    private void initTemp() {
        tempGroups = new ArrayList<Group>();
        tempGroups.add(new Group("1"));
        tempGroups.get(0).addStudent(new Student("Ivanov"));
        tempGroups.get(0).addStudent(new Student("Sidorov"));
        tempGroups.get(0).addAcademicSubject("History");
        tempGroups.get(0).addAcademicSubject("Maths");
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

    @Before
    public void setUp() {
        initTemp();
        initGroups();
    }

    @Test
    public void canGetGroups() {
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test
    public void canAddGroup() {
        tempGroups.add(new Group("2"));
        tableOfMarks.addGroup(new Group("2"));
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test
    public void whenGroupAlreadyExists() {
        tableOfMarks.addGroup(new Group("1"));
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test
    public void canAddStudent() {
        tempGroups.get(0).addStudent(new Student("Kornyakov"));
        tableOfMarks.addStudent(new Group("1"), new Student("Kornyakov"));
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileAddingStudent() {
        tableOfMarks.addStudent(new Group("2"), new Student("Kornyakov"));
    }

    @Test
    public void canAddSubject() {
        tempGroups.get(0).addAcademicSubject("Science");
        tableOfMarks.addAcademicSubject(new Group("1"), "Science");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void  whenRequiredGroupDoesNotExistWhileAddingAcademicSubject() {
        tableOfMarks.addAcademicSubject(new Group("2"), "Maths");
    }

    @Test
    public void canAddNewMark() {
        tempGroups.get(0).addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"));
        tableOfMarks.addNewMark(
                new Mark(4, "History", new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"), new Group("1"));
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileAddingNewMark() {
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
    public void whenRequiredGroupDoesNotExistWhileGettingMark() {
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
    public void whenRequiredGroupDoesNotExistWhileDeletingMark() {
        tableOfMarks.deleteMark(new Group("2"), new Student("Petrov"),  "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteGroup() {
        comparedTableOfMarks.deleteGroup(new Group("2"));
        assertEquals(tableOfMarks, comparedTableOfMarks);
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingGroup() {
        tableOfMarks.deleteGroup(new Group("2"));
    }

    @Test
    public void canDeleteStudent() {
        tempGroups.get(0).deleteStudent(new Student("Sidorov"));
        tableOfMarks.deleteStudent(new Group("1"), new Student("Sidorov"));
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingStudent() {
        tableOfMarks.deleteStudent(new Group("2"), new Student("Sidorov"));
    }

    @Test
    public void canDeleteAcademicSubject() {
        tempGroups.get(0).deleteAcademicSubject("History");
        tableOfMarks.deleteAcademicSubject(new Group("1"), "History");
        assertEquals(tempGroups, tableOfMarks.getGroups());
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void whenRequiredGroupDoesNotExistWhileDeletingAcademicSubject() {
        tableOfMarks.deleteAcademicSubject(new Group("2"), "History");
    }

}
