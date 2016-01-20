package ru.unn.agile.MarksAccounting.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateParser {
    private DateParser() { }

    public static GregorianCalendar parseDate(final String stringDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        dateFormat.setLenient(false);
        ParsePosition position = new ParsePosition(0);
        Date date = dateFormat.parse(stringDate, position);
        if (position.getIndex() != stringDate.length()) {
            throw new ParseException("Can't parse date!", -1);
        }
        GregorianCalendar resultCalendar = new GregorianCalendar();
        resultCalendar.setTime(date);
        return resultCalendar;
    }

    public static String formatDate(final GregorianCalendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        dateFormat.setCalendar(date);
        return dateFormat.format(date.getTime());
    }
}
