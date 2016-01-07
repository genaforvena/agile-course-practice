package ru.unn.agile.MarksAccounting.model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;

public class TestStudent {
    private Student ivanov;
    private Student comparedIvanov;
    private ArrayList<Mark> marks;

    @Before
    public void setUp() {
        initMarks();
        initStudents();
    }

    private void initMarks() {
        marks = new ArrayList<Mark>();
        marks.add(new Mark(3, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 30)));
        marks.add(new Mark(5, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
        marks.add(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 11)));
        marks.add(new Mark(4, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31)));
    }

    private void initStudents() {
        ivanov = new Student("Ivanov Ivan Ivanovich");
        comparedIvanov = new Student("Ivanov Ivan Ivanovich");
        ivanov.addMark(marks.get(0));
        ivanov.addMark(marks.get(2));
        ivanov.addMark(marks.get(3));
        comparedIvanov.addMark(marks.get(0));
        comparedIvanov.addMark(marks.get(1));
        comparedIvanov.addMark(marks.get(2));
        comparedIvanov.addMark(marks.get(3));
    }

    @Test
    public void canGetName() {
        assertEquals("Ivanov Ivan Ivanovich", ivanov.getName());
    }

    @Test
    public void canGetMarks() {
        assertEquals(marks, comparedIvanov.getMarks());
    }

    @Test
    public void canGetDates() {
        ArrayList<GregorianCalendar> expectedDates = new ArrayList<GregorianCalendar>();
        expectedDates.add(new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 30));
        expectedDates.add(new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31));

        assertEquals(expectedDates, ivanov.getDates("Maths"));
    }

    @Test
    public void canIndicateWhenMarksExist() {
        assertTrue(ivanov.doMarksExist());
    }

    @Test
    public void canIndicateWhenMarksDoNotExist() {
        Student sidorov = new Student("Sidorov");

        assertFalse(sidorov.doMarksExist());
    }

    @Test
    public void canIndicateWhenMarksInRequiredSubjectExist() {
        assertTrue(ivanov.doMarksInSubjectExist("Maths"));
    }

    @Test
    public void canIdicateWhenMarksInRequiredSubjectDoNotExist() {
        assertFalse(ivanov.doMarksInSubjectExist("Science"));
    }

    @Test
    public void canAddNewMark() {
        ivanov.addMark(new Mark(5, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));

        assertEquals(marks, ivanov.getMarks());
    }

    @Test(expected = NoMarkCorrectionException.class)
    public void canNotChangeMark() {
        comparedIvanov.addMark(new Mark(5, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31)));
    }

    @Test
    public void whenEquals() {
        Student equivalentIvanov = new Student("Ivanov Ivan Ivanovich");
        equivalentIvanov.addMark(new Mark(3, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 30)));
        equivalentIvanov.addMark(new Mark(5, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
        equivalentIvanov.addMark(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 11)));
        equivalentIvanov.addMark(new Mark(4, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31)));

        assertTrue(comparedIvanov.equals(equivalentIvanov));
    }

    @Test
    public void whenDoesNotEqual() {
        assertFalse(ivanov.equals(comparedIvanov));
    }

    @Test
    public void canGetMark() {
        Mark four = new Mark(4, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31));

        assertEquals(four, ivanov.getMark("Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31)));
    }

    @Test(expected = MarkDoesNotExistException.class)
    public void canNotGetMarkWhenMarkDoesNotExist() {
        ivanov.getMark("Maths", new GregorianCalendar(2015, GregorianCalendar.MARCH, 31));
    }

    @Test
    public void canDeleteMark() {
        comparedIvanov.deleteMark("Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));

        assertEquals(ivanov, comparedIvanov);
    }

    @Test(expected = MarkDoesNotExistException.class)
    public void canNotDeleteMarkWhenMarkDoesNotExist() {
        comparedIvanov.deleteMark("Science",
                new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 1));
    }

    @Test
    public void canDeleteMarks() {
        comparedIvanov.deleteMarks("Maths");

        marks.remove(3);
        marks.remove(0);

        assertEquals(comparedIvanov.getMarks(), marks);
    }
}
