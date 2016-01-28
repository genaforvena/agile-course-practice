package ru.unn.agile.MarksAccounting.model;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestDateParser {
    @Test
    public void canParseDate() {
        try {
            GregorianCalendar date = DateParser.parseDate("10-05-2015");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setCalendar(date);

            assertEquals("10-05-2015", dateFormat.format(date.getTime()));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canFormatDate() {
        GregorianCalendar date = new GregorianCalendar(2015, GregorianCalendar.MAY, 10);

        assertEquals("10-05-2015", DateParser.formatDate(date));
    }
}
