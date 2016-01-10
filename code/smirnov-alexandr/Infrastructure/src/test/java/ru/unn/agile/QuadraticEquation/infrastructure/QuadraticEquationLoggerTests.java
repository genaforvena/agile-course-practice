package ru.unn.agile.QuadraticEquation.infrastructure;

import org.junit.Before;
import org.junit.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class QuadraticEquationLoggerTests {
    private static final String FILE_NAME = "./LoggerTests.txt";
    private QuadraticEquationLogger qeLogger;

    @Before
    public void setUp() {
        qeLogger = new QuadraticEquationLogger(FILE_NAME);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException e) {
            fail(FILE_NAME + " not found!");
        }
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(qeLogger);
    }

    @Test
    public void canAppendMessageToLog() {
        String testMessage = "message";
        qeLogger.log(testMessage);
        String message = qeLogger.getLog().get(0);
        assertTrue(message.contains(testMessage));
    }

    @Test
    public void canLogSomeMessages() {
        String[] messages = {"1", "2", "3"};
        for (String message : messages) {
            qeLogger.log(message);
        }
        List<String> addedMessages = qeLogger.getLog();
        for (int i = 0; i < addedMessages.size(); i++) {
            assertTrue(addedMessages.get(i).contains(messages[i]));
        }
    }
}

