package ru.unn.agile.Queue.ViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FakeLoggerTests {
    private FakeQueueLogger fakeQueueLogger;

    @Before
    public void setUpLoggerForTests() {
        fakeQueueLogger = new FakeQueueLogger();
    }

    @Test
    public void canAddRecordsToFakeLogger() {
        final String record = "Something";

        fakeQueueLogger.addRecord(record);

        assertEquals(fakeQueueLogger.getAllRecords().get(0), record);
    }

    @Test
    public void canAddSeveralRecords() {
        String[] records = {"Some", "messages", "for", "test"};
        boolean allRecordsLogged = true;

        for (int i = 0; i < records.length; i++) {
            fakeQueueLogger.addRecord(records[i]);
        }
        List<String> log = fakeQueueLogger.getAllRecords();
        for (int i = 0; i < log.size(); i++) {
            if (!log.get(i).contains(records[i])) {
                allRecordsLogged = false;
            }
        }
        assertTrue(allRecordsLogged);
    }

}
