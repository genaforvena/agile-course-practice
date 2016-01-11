package ru.unn.agile.MarksAccounting.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMark {

    private Mark five;

    @Before
    public void setUp() {
        five = new Mark(5, "Maths", new GregorianCalendar(2015, Calendar.OCTOBER, 31));
    }

    @Test(expected = MarkIsNotPositiveException.class)
    public void cannotInitNonPositiveMark() {
        five = new Mark(-2, "Maths", new GregorianCalendar(2015, Calendar.OCTOBER, 31));
    }

    @Test
    public void canGetAcademicSubject() {
        assertEquals("Maths", five.getAcademicSubject());
    }

    @Test
    public void canGetDate() {
        assertEquals(new GregorianCalendar(2015, Calendar.OCTOBER, 31), five.getDate());
    }

    @Test
    public void canGetValue() {
        assertEquals(5, five.getValue());
    }

    @Test
    public void whenEquals() {
        Mark equivalentFive = new Mark(5, "Maths",
                new GregorianCalendar(2015, Calendar.OCTOBER, 31));
        assertTrue(five.equals(equivalentFive));
    }

    @Test
    public void whenDoesNotEqual() {
        Mark four = new Mark(4, "Maths", new GregorianCalendar(2015, Calendar.OCTOBER, 31));
        assertFalse(five.equals(four));
    }

    @Test
    public void canIdentifyMarkCorrection() {
        assertTrue(five.isMarkCorrection(new Mark(4, "Maths",
                new GregorianCalendar(2015, Calendar.OCTOBER, 31))));
    }

    @Test
    public void ifSubjectIsNotTheSame() {
        assertFalse(five.isMarkCorrection(new Mark(4, "History",
                new GregorianCalendar(2015, Calendar.OCTOBER, 31))));
    }

    @Test
    public void ifDateIsNotTheSame() {
        assertFalse(five.isMarkCorrection(new Mark(4, "Maths",
                new GregorianCalendar(2015, Calendar.DECEMBER, 31))));
    }

    @Test
    public void ifBothAreNotTheSame() {
        assertFalse(five.isMarkCorrection(new Mark(4, "History",
                new GregorianCalendar(2015, Calendar.DECEMBER, 31))));
    }
}
