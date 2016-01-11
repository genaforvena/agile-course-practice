package ru.unn.agile.MarksAccounting.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGroup {

    private Group group1;
    private Group comparedGroup1;
    private ArrayList<Student> tempStudents;
    private ArrayList<String> tempAcademicSubjects;

    private void initTemps() {
        tempStudents = new ArrayList<Student>();
        tempStudents.add(new Student("Ivanov"));
        tempStudents.add(new Student("Petrov"));
        tempStudents.add(new Student("Sidorov"));
        tempStudents.get(2).addMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
        tempAcademicSubjects = new ArrayList<String>();
        tempAcademicSubjects.add("History");
        tempAcademicSubjects.add("Maths");
        tempAcademicSubjects.add("Science");
    }

    private void initGroups() {
        group1 = new Group("1");
        comparedGroup1 = new Group("1");
        group1.addStudent(tempStudents.get(0));
        group1.addStudent(tempStudents.get(1));
        comparedGroup1.addStudent(tempStudents.get(0));
        comparedGroup1.addStudent(tempStudents.get(1));
        comparedGroup1.addStudent(tempStudents.get(2));
        group1.addAcademicSubject(tempAcademicSubjects.get(0));
        group1.addAcademicSubject(tempAcademicSubjects.get(2));
        comparedGroup1.addAcademicSubject(tempAcademicSubjects.get(0));
        comparedGroup1.addAcademicSubject(tempAcademicSubjects.get(1));
        comparedGroup1.addAcademicSubject(tempAcademicSubjects.get(2));
        comparedGroup1.addNewMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)), new Student("Sidorov"));
    }

    @Before
    public void setUp() {
        initTemps();
        initGroups();
    }

    @Test
    public void canGetNumber() {
        assertEquals(group1.getNumber(), "1");
    }

    @Test
    public void canGetStudents() {
        assertEquals(tempStudents, comparedGroup1.getStudents());
    }

    @Test
    public void canGetSubjects() {
        assertEquals(tempAcademicSubjects, comparedGroup1.getAcademicSubjects());
    }

    @Test
    public void canAddNewStudent() {
        tempStudents.get(2).deleteMarks("History");
        group1.addStudent(new Student("Sidorov"));
        assertEquals(tempStudents, group1.getStudents());
    }

    @Test
    public void whenStudentAlreadyExists() {
        comparedGroup1.addStudent(new Student("Petrov"));
        assertEquals(tempStudents, comparedGroup1.getStudents());
    }

    @Test
    public void canAddNewAcademicSubject() {
        group1.addAcademicSubject("Maths");
        assertEquals(tempAcademicSubjects, group1.getAcademicSubjects());
    }

    @Test
    public void whenAcademicSubjectAlreadyExists() {
        comparedGroup1.addAcademicSubject("History");
        assertEquals(tempAcademicSubjects, comparedGroup1.getAcademicSubjects());
    }

    @Test
    public void canAddNewMark() {
        group1.addStudent(new Student("Sidorov"));
        group1.addNewMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"));
        assertEquals(tempStudents, group1.getStudents());
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void whenRequiredStudentDoesNotExistWhileAddingMark() {
        group1.addNewMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void whenRequiredAcademicSubjectDoesNotExistWhileAddingMark() {
        group1.addNewMark(new Mark(4, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Petrov"));
    }

    @Test
    public void whenEquals() {
        Group equivalentomparedGroup1 = new Group("1");
        equivalentomparedGroup1.addStudent(new Student("Ivanov"));
        equivalentomparedGroup1.addStudent(new Student("Petrov"));
        equivalentomparedGroup1.addAcademicSubject("History");
        equivalentomparedGroup1.addAcademicSubject("Science");
        assertTrue(equivalentomparedGroup1.equals(group1));
    }

    @Test
    public void whenDoesNotEqual() {
        Group equivalentomparedGroup1 = new Group("1");
        equivalentomparedGroup1.addStudent(new Student("Ivanov"));
        equivalentomparedGroup1.addStudent(new Student("Petrov"));
        equivalentomparedGroup1.addAcademicSubject("History");
        equivalentomparedGroup1.addAcademicSubject("Science");
        assertFalse(equivalentomparedGroup1.equals(comparedGroup1));
    }

    @Test
    public void canGetMark() {
        Mark four = new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
        assertEquals(four, comparedGroup1.getMark(new Student("Sidorov"), "History",
                        new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void whenRequiredStudentDoesNotExistWhileGettingMark() {
        group1.getMark(new Student("Sidorov"), "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void whenRequiredAcademicSubjectDoesNotExistWhileGettingMark() {
        group1.getMark(new Student("Petrov"), "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteMark() {
        group1.addStudent(new Student("Sidorov"));
        group1.addAcademicSubject("Maths");
        comparedGroup1.deleteMark(new Student("Sidorov"), "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
        assertEquals(group1, comparedGroup1);
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void whenRequiredStudentDoesNotExistWhileDeletingMark() {
        group1.deleteMark(new Student("Sidorov"), "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void whenRequiredAcademicSubjectDoesNotExistWhileDeletingMark() {
        group1.deleteMark(new Student("Petrov"), "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test
    public void canDeleteStudent() {
        group1.addAcademicSubject("Maths");
        comparedGroup1.deleteStudent(new Student("Sidorov"));
        assertEquals(group1, comparedGroup1);
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void whenRequiredStudentDoesNotExistWhileDeletingStudent() {
        group1.deleteStudent(new Student("Sidorov"));
    }

    @Test
    public void canDeleteAcademicSubject() {
        group1.addStudent(new Student("Sidorov"));
        tempAcademicSubjects.remove(0);
        comparedGroup1.deleteAcademicSubject("History");
        assertTrue(group1.getStudents().equals(comparedGroup1.getStudents())
                && tempAcademicSubjects.equals(comparedGroup1.getAcademicSubjects()));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void whenRequiredAcademicSubjectDoesNotExistWhileDeletingAcademicSubject() {
        group1.deleteAcademicSubject("Maths");
    }

}
