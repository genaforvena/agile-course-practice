package ru.unn.agile.ElasticityOfDemand.log;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ElasticityOfDemand.log.mock.WriterMock;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by imozerov on 02.03.16.
 */
public class LoggerTest {
    private Logger loggerUnderTest;

    private WriterMock writerMock;

    @Before
    public void setUp() throws Exception {
        writerMock = new WriterMock();
        loggerUnderTest = new Logger(writerMock);
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        loggerUnderTest.log(testMessage);

        String logOutput = writerMock.getLogMessages().get(0);
        assertTrue(logOutput.contains(testMessage));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        for (String message: messages) {
            loggerUnderTest.log(message);
        }

        List<String> logOutput = writerMock.getLogMessages();
        for (int i = 0; i < messages.length; i++) {
            assertTrue(logOutput.get(i).contains(messages[i]));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        loggerUnderTest.log(testMessage);

        String logoutPut = writerMock.getLogMessages().get(0);
        assertTrue(logoutPut.matches("(?s)^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .+"));
    }
}
