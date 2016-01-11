package ru.unn.agile.MarksAccounting.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TestStudent {

    private Student ivanov;
    private Student comparedIvanov;
    private ArrayList<Mark> temp;

    private void initTemp() {
        temp = new ArrayList<Mark>();
        temp.add(new Mark(3, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.SEPTEMBER, 30)));
        temp.add(new Mark(5, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
        temp.add(new Mark(4, "History",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 11)));
        temp.add(new Mark(4, "Maths",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 31)));
    }

    private void initStudents() {
        ivanov = new Student("Ivanov Ivan Ivanovich");
        comparedIvanov = new Student("Ivanov Ivan Ivanovich");
        ivanov.addMark(temp.get(0));
        ivanov.addMark(temp.get(2));
        ivanov.addMark(temp.get(3));
        comparedIvanov.addMark(temp.get(0));
        comparedIvanov.addMark(temp.get(1));
        comparedIvanov.addMark(temp.get(2));
        comparedIvanov.addMark(temp.get(3));
    }

    @Before
    public void setUp() {
        initTemp();
        initStudents();
    }

    @Test
    public void canGetName() {
        assertEquals("Ivanov Ivan Ivanovich", ivanov.getName());
    }

    @Test
    public void canGetMarks() {
        assertEquals(temp, comparedIvanov.getMarks());
    }

    @Test
    public void canAddNewMark() {
        ivanov.addMark(new Mark(5, "Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1)));
        assertEquals(temp, ivanov.getMarks());
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
    public void whenMarkIsAbsent() {
        ivanov.getMark("Maths", new GregorianCalendar(2015, GregorianCalendar.MARCH, 31));
    }

    @Test
    public void canDeleteMark() {
        comparedIvanov.deleteMark("Science",
                new GregorianCalendar(2015, GregorianCalendar.OCTOBER, 1));
        assertEquals(ivanov, comparedIvanov);
    }

    @Test(expected = MarkDoesNotExistException.class)
    public void whenCanNotDelete() {
        comparedIvanov.deleteMark("Science",
                new GregorianCalendar(2015, GregorianCalendar.DECEMBER, 1));
    }

    @Test
    public void canDeleteMarks() {
        comparedIvanov.deleteMarks("Maths");
        temp.remove(3);
        temp.remove(0);
        assertEquals(comparedIvanov.getMarks(), temp);
    }
}
