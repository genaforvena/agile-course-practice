package ru.unn.agile.Minesweeper.Infrastructure;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import static ru.unn.agile.Minesweeper.Infrastructure.RegexMatcher.matchesPattern;

public class TxtLoggerTest {
    private static final String NAME_OF_FILE = "./TxtLogger_test_out-lab3.log";
    private static final String NO_VALID_FILE_NAME = "/:*:/";
    private static final String DATE_TIME_PATTERN = "^\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2}:";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(NAME_OF_FILE);
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
        assertNotNull(txtLogger);
    }


    @Test
    public void canWriteLogMessage() {
        String testMessage = "Тестовое сообщение";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern(DATE_TIME_PATTERN + " " + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] testMessages = {"Тестовое сообщение 1", "Тестовое сообщение 2"};

        txtLogger.log(testMessages[0]);
        txtLogger.log(testMessages[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i),
                       matchesPattern(DATE_TIME_PATTERN + " " + testMessages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Тестовое сообщение";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertThat(message, matchesPattern(DATE_TIME_PATTERN + " .*"));
    }

    @Test
    public void openNoValidLogFile() {
        new TxtLogger(NO_VALID_FILE_NAME);
    }
}
