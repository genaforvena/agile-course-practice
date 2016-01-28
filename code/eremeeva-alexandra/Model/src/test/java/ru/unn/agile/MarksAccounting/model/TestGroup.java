package ru.unn.agile.MarksAccounting.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGroup {
    private Group group1;
    private Group comparedGroup1;
    private ArrayList<Student> students;
    private ArrayList<String> academicSubjects;

    private void initArrays() {
        students = new ArrayList<Student>();
        students.add(new Student("Ivanov"));
        students.add(new Student("Petrov"));
        students.add(new Student("Sidorov"));
        students.get(2).addMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
        academicSubjects = new ArrayList<String>();
        academicSubjects.add("History");
        academicSubjects.add("Maths");
        academicSubjects.add("Science");
    }

    private void initGroups() {
        group1 = new Group("1");
        comparedGroup1 = new Group("1");
        group1.addStudent(students.get(0));
        group1.addStudent(students.get(1));
        comparedGroup1.addStudent(students.get(0));
        comparedGroup1.addStudent(students.get(1));
        comparedGroup1.addStudent(students.get(2));
        group1.addAcademicSubject(academicSubjects.get(0));
        group1.addAcademicSubject(academicSubjects.get(2));
        comparedGroup1.addAcademicSubject(academicSubjects.get(0));
        comparedGroup1.addAcademicSubject(academicSubjects.get(1));
        comparedGroup1.addAcademicSubject(academicSubjects.get(2));
        comparedGroup1.addNewMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)), new Student("Sidorov"));
    }

    @Before
    public void setUp() {
        initArrays();
        initGroups();
    }

    @Test
    public void canGetNumber() {
        assertEquals(group1.getNumber(), "1");
    }

    @Test
    public void canGetStudents() {
        assertEquals(students, comparedGroup1.getStudents());
    }

    @Test
    public void canGetStudentsAsArray() {
        Student[] studentsArray = {new Student("Ivanov"), new Student("Petrov")};

        assertArrayEquals(studentsArray, group1.getStudentsAsArray());
    }

    @Test
    public void canGetStudentsAsArrayOfStrings() {
        String[] studentsArray = {"Ivanov", "Petrov"};

        assertArrayEquals(studentsArray, group1.getStudentsAsArrayOfStrings());
    }

    @Test
    public void canGetStudentsWhereMarksExistAsArrayOfStrings() {
        String[] studentsWithMarksArray = {"Sidorov"};

        assertArrayEquals(studentsWithMarksArray,
                comparedGroup1.getStudentsWhereMarksExistAsArrayOfStrings());
    }

    @Test
    public void canIndicateWhenStudentsExist() {
        assertTrue(group1.doStudentsExist());
    }

    @Test
    public void canIndicateWhenStudentsDoNotExist() {
        Group group2 = new Group("2");

        assertFalse(group2.doStudentsExist());
    }

    @Test
    public void canGetSubjects() {
        assertEquals(academicSubjects, comparedGroup1.getAcademicSubjects());
    }

    @Test
    public void canGetSubjectsAsArray() {
        String[] subjectsArray = {"History", "Science"};

        assertArrayEquals(subjectsArray, group1.getAcademicSubjectsAsArray());
    }

    @Test
    public void canGetAcademicSubjectsWhereMarksExistAsArray() {
        String[] subjectsWhereMarksExist = {"History"};

        assertArrayEquals(subjectsWhereMarksExist,
                comparedGroup1.getAcademicSubjectsWhereMarksExistAsArray(new Student("Sidorov")));
    }

    @Test
    public void canIndicateWhenAcademicSubjectsExist() {
        assertTrue(group1.doAcademicSubjectsExist());
    }

    @Test
    public void canIndicateWhenAcademicSubjectsDoNotExist() {
        Group group2 = new Group("2");

        assertFalse(group2.doAcademicSubjectsExist());
    }

    @Test
    public void canIndicateWhenMarksExist() {
        assertTrue(comparedGroup1.doMarksExist());
    }

    @Test
    public void canIndicateWhenMarksDoNotExist() {
        assertFalse(group1.doMarksExist());
    }

    @Test
    public void canGetDates() {
        ArrayList<GregorianCalendar> dates = new ArrayList<GregorianCalendar>();
        dates.add(new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));

        assertEquals(dates, comparedGroup1.getDates("History"));
    }

    @Test (expected = AcademicSubjectDoesNotExistException.class)
    public void canNotGetDatesWhenSubjectDoesNotExist() {
        group1.getDates("Maths");
    }

    @Test (expected = NoStudentExistsException.class)
    public void canNotGetDatesWhenNoStudentExists() {
        Group group2 = new Group("2");
        group2.addAcademicSubject("Maths");

        group2.getDates("Maths");
    }

    @Test (expected = NoMarkExistsException.class)
    public void canNotGetDatesWhenNoMarkExists() {
        group1.getDates("History");
    }

    @Test
    public void canGetDatesAsArray() {
        GregorianCalendar[] dates = {new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)};

        assertArrayEquals(dates, comparedGroup1.getDatesAsArray("History"));
    }

    @Test (expected = AcademicSubjectDoesNotExistException.class)
    public void canNotGetDatesAsArrayWhenSubjectDoesNotExist() {
        group1.getDatesAsArray("Maths");
    }

    @Test (expected = NoStudentExistsException.class)
    public void canNotGetDatesAsArrayWhenNoStudentExists() {
        Group group2 = new Group("2");
        group2.addAcademicSubject("Science");

        group2.getDatesAsArray("Science");
    }

    @Test (expected = NoMarkExistsException.class)
    public void canNotGetDatesAsArrayWhenNoMarkExists() {
        group1.getDatesAsArray("History");
    }

    @Test
    public void canIndicateWhenMarksInRequiredSubjectExist() {
        assertTrue(comparedGroup1.doMarksInSubjectExist("History"));
    }

    @Test
    public void canIndicateWhenMarksInRequiredSubjectDoNotExist() {
        assertFalse(comparedGroup1.doMarksInSubjectExist("Maths"));
    }

    @Test
    public void canAddStudent() {
        students.get(2).deleteMarks("History");

        group1.addStudent(new Student("Sidorov"));

        assertEquals(students, group1.getStudents());
    }

    @Test (expected = StudentAlreadyExistsException.class)
    public void canNotAddStudentWhenStudentAlreadyExists() {
        comparedGroup1.addStudent(new Student("Petrov"));
    }

    @Test
    public void canAddAcademicSubject() {
        group1.addAcademicSubject("Maths");

        assertEquals(academicSubjects, group1.getAcademicSubjects());
    }

    @Test(expected = SubjectAlreadyExistsException.class)
    public void canNotAddSubjectWhenSubjectAlreadyExists() {
        comparedGroup1.addAcademicSubject("History");
    }

    @Test
    public void canAddNewMark() {
        group1.addStudent(new Student("Sidorov"));
        group1.addNewMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"));

        assertEquals(students, group1.getStudents());
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void canNotAddMarkWhenStudentDoesNotExist() {
        group1.addNewMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Sidorov"));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void canNotAddMarkWhenAcademicSubjectDoesNotExist() {
        group1.addNewMark(new Mark(4, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)),
                new Student("Petrov"));
    }

    @Test
    public void whenEquals() {
        Group equivalentComparedGroup1 = new Group("1");
        equivalentComparedGroup1.addStudent(new Student("Ivanov"));
        equivalentComparedGroup1.addStudent(new Student("Petrov"));
        equivalentComparedGroup1.addAcademicSubject("History");
        equivalentComparedGroup1.addAcademicSubject("Science");

        assertTrue(equivalentComparedGroup1.equals(group1));
    }

    @Test
    public void whenDoesNotEqual() {
        Group equivalentComparedGroup1 = new Group("1");
        equivalentComparedGroup1.addStudent(new Student("Ivanov"));
        equivalentComparedGroup1.addStudent(new Student("Petrov"));
        equivalentComparedGroup1.addAcademicSubject("History");
        equivalentComparedGroup1.addAcademicSubject("Science");

        assertFalse(equivalentComparedGroup1.equals(comparedGroup1));
    }

    @Test
    public void canGetMark() {
        Mark four = new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));

        assertEquals(four, comparedGroup1.getMark(new Student("Sidorov"), "History",
                        new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void canNotGetMarkWhenStudentDoesNotExist() {
        group1.getMark(new Student("Sidorov"), "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void canNotGetMarkWhenAcademicSubjectDoesNotExist() {
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
    public void canNotDeleteMarkWhenRequiredStudentDoesNotExist() {
        group1.deleteMark(new Student("Sidorov"), "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void canNotDeleteMarkWhenAcademicSubjectDoesNotExist() {
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
    public void canNotDeleteStuddentWhenStudentDoesNotExist() {
        group1.deleteStudent(new Student("Sidorov"));
    }

    @Test
    public void canDeleteAcademicSubject() {
        group1.addStudent(new Student("Sidorov"));

        academicSubjects.remove(0);
        comparedGroup1.deleteAcademicSubject("History");

        assertTrue(group1.getStudents().equals(comparedGroup1.getStudents())
                && academicSubjects.equals(comparedGroup1.getAcademicSubjects()));
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void canNotDeleteAcademicSubjectWhenAcademicSubjectDoesNotExist() {
        group1.deleteAcademicSubject("Maths");
    }

}
