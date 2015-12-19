package ru.unn.agile.Metrics.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TextLoggerTests {
    private TextLogger logger;

    @Before
    public void setUp() {
        logger = new TextLogger("./TestTextLogger.log");
    }

    @Test
    public void canConstructTextLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canWriteMessageInLog() {
        String testMessage = "Test: [-1.0]; TEST";

        logger.add(testMessage);

        assertTrue(getLastLogMessage().contains(testMessage));
    }

    @Test
    public void canWriteSeveralMessagesInLog() {
        String[] testMessages = {"Test1", "Test2"};

        logger.add(testMessages[0]);
        logger.add(testMessages[1]);

        ArrayList<String> log = logger.getLog();
        assertTrue(log.size() == 2);
    }

    @Test
    public void logMessagesAreInCorrectOrder() {
        String[] testMessages = {"Test1", "Test2"};

        logger.add(testMessages[0]);
        logger.add(testMessages[1]);

        assertTrue(getLastLogMessage().matches(".*" + testMessages[1] + "$"));
    }

    @Test
    public void logContainsDateAndTime() {
        String testMessage = "crap";

        logger.add(testMessage);

        assertTrue(getLastLogMessage().matches("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    private String getLastLogMessage() {
        ArrayList<String> log = logger.getLog();
        return log.get(log.size() - 1);
    }
}
