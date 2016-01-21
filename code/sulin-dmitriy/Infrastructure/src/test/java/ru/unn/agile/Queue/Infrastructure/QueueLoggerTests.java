package ru.unn.agile.Queue.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class QueueLoggerTests {
    private static String nameOfFile = "./log.txt";
    private QueueLogger logger;

    @Before
    public void setUpLogger() {
        logger = new QueueLogger(nameOfFile);
    }

    @Test
    public void canCreateLogger() {
         assertNotNull(logger);
    }

    @Test
    public void canAddRecord() {
        String record = "SOMETHING";

        logger.addRecord(record);

        assertThat(logger.getAllRecords().get(0), containsString(record));
    }

    @Test (expected = IllegalArgumentException.class)
    public void cannotCreateLoggerWithoutFilename() {
       logger = new QueueLogger(null);
    }

    @Test
    public void canAddSeveralRecords() {
        String[] records = {"Some", "messages", "for", "test"};
        boolean areAllRecordsLogged = true;

        for (int i = 0; i < records.length; i++) {
            logger.addRecord(records[i]);
        }
        List<String> log = logger.getAllRecords();
        for (int i = 0; i < log.size(); i++) {
            if (!log.get(i).contains(records[i])) {
                areAllRecordsLogged = false;
                break;
            }
        }
        assertTrue(areAllRecordsLogged);
    }
}
