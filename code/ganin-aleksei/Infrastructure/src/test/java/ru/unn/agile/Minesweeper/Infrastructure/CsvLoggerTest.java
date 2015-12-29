package ru.unn.agile.Minesweeper.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;

public class CsvLoggerTest {
    private static final String NAME_OF_FILE = "./CsvLogger_test_out-lab3.log";
    private static final String NO_VALID_FILE_NAME = "/:*:/";
    private static final String DATE_TIME_PATTERN = "\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2}";
    private CsvLogger csvLogger;

    @Before
    public void setUp() {
        csvLogger = new CsvLogger(NAME_OF_FILE);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(NAME_OF_FILE));
        } catch (FileNotFoundException e) {
            fail("Log file " + NAME_OF_FILE + " wasn't found!");
        }
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(csvLogger);
    }


    @Test
    public void canWriteLogMessage() {
        String testMessage = "Тестовое сообщение";

        csvLogger.log(testMessage);

        String message = csvLogger.getLog().get(0);
        assertTrue(message.matches(DATE_TIME_PATTERN + ": " + testMessage));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] testMessages = {"Тестовое сообщение 1", "Тестовое сообщение 2"};

        csvLogger.log(testMessages[0]);
        csvLogger.log(testMessages[1]);

        List<String> actualMessages = csvLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertTrue(actualMessages.get(i).matches(DATE_TIME_PATTERN + ": " + testMessages[i]));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Тестовое сообщение";

        csvLogger.log(testMessage);

        String message = csvLogger.getLog().get(0);
        assertTrue(message.matches(DATE_TIME_PATTERN + ": .*"));
    }

    @Test
    public void openNoValidLogFile() {
        new CsvLogger(NO_VALID_FILE_NAME);
    }
}
