package ru.unn.agile.Queue.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class QueueLoggerTests {
    private static String fileName = "./log.txt";
    private QueueLogger logger;

    @Before
    public void setUpLoggerForTests() {
        logger = new QueueLogger(fileName);
    }

    @Test
    public void canCreateLogger() {
        final String fileName = "./lol.txt";
        QueueLogger newLogger = new QueueLogger(fileName);

        assertNotNull(newLogger);
    }

    @Test
    public void canAddRecord() {
        String message = "SOMETHING";

        logger.addRecord(message);

        assertThat(logger.getAllRecords().get(0), containsString(message));
    }

    @Test (expected = IllegalArgumentException.class)
    public void cannotCreateLoggerWithoutFilename() {
       logger = new QueueLogger(null);
    }

    @Test
    public void canAddSeveralRecords() {
        String[] records = {"Some", "messages", "for", "test"};
        boolean allRecordsLogged = true;

        for (int i = 0; i < records.length; i++) {
            logger.addRecord(records[i]);
        }
        List<String> log = logger.getAllRecords();
        for (int i = 0; i < log.size(); i++) {
            if (!log.get(i).contains(records[i])) {
                allRecordsLogged = false;
            }
        }
        assertTrue(allRecordsLogged);
    }
}
