package ru.unn.agile.MarksAccounting.viewmodel;

import static org.junit.Assert.fail;

public class ExceptionsChecker {
    private ExceptionsChecker() { }

    public static void checkException(final Exception caughtException,
                                      final RuntimeException expectedException) {
        if (caughtException.getClass().equals(expectedException.getClass())) {
            throw expectedException;
        } else {
            fail();
        }
    }
}
