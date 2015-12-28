package ru.unn.agile.Metrics.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CsvLoggerTests {
    private CsvLogger logger;

    @Before
    public void setUp() {
        logger = new CsvLogger("./TestRealLogger.csv");
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

        List<String> log = logger.get();
        assertTrue(log.size() == 2);
    }

    @Test
    public void canCorrectlyGetLastMessage() {
        String[] testMessages = {"Test1", "Test2"};

        logger.add(testMessages[0]);
        logger.add(testMessages[1]);

        assertTrue(getLastLogMessage().contains(testMessages[1]));
    }

    @Test
    public void logContainsDateAndTime() {
        String testMessage = "crap";

        logger.add(testMessage);

        assertTrue(getLastLogMessage().matches("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2}.*"));
    }

    private String getLastLogMessage() {
        List<String> log = logger.get();
        return log.get(log.size() - 1);
    }
}
