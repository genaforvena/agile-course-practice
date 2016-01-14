package ru.unn.agile.Triangle.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TxtTriangleLoggerTests {
    private TxtTriangleLogger txtLogger;
    private static final String NAME_OF_LOG_FILE = "./TriangleLog.log";

    @Before
    public void setUpLogger() {
        txtLogger = new TxtTriangleLogger(NAME_OF_LOG_FILE);
    }

    @Test
    public void canFindLogFile() {
        try {
            new BufferedReader(new FileReader(NAME_OF_LOG_FILE));
        } catch (FileNotFoundException e) {
            fail("Error: log file wasn't found");
        }
    }

    @Test
    public void canWriteToLogFile() {
        String message = "Message to log file";

        txtLogger.add(message);
        String recordFromLogFile = txtLogger.getLog().get(0);

        assertTrue(recordFromLogFile.contains(message));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] messages = {"the first action", "the second action", "the third action"};
        boolean areMessagesLogged = true;

        for (int i = 0; i < messages.length; i++) {
            txtLogger.add(messages[i]);
        }
        List<String> log = txtLogger.getLog();
        for (int i = 0; i < log.size(); i++) {
            if (!log.get(i).contains(messages[i])) {
                areMessagesLogged = false;
            }
        }

        assertTrue(areMessagesLogged);
    }

    @Test
    public void canSaveDateAndTime() {
        txtLogger.add("Action was done");

        String recordFromLogFile = txtLogger.getLog().get(0);

        assertTrue(Pattern.matches("^.\\d{2}-[A-Za-z]{3}-\\d{4} \\d{2}:\\d{2}:\\d{2}.*",
                recordFromLogFile));
    }
}
