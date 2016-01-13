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
    private QuadraticEquationLogger quadraticEquationLogger;

    @Before
    public void setUp() {
        quadraticEquationLogger = new QuadraticEquationLogger(FILE_NAME);
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException fileNotFoundException) {
            fail(String.format("%s not found!", FILE_NAME));
        }
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(quadraticEquationLogger);
    }

    @Test
    public void canAppendMessageToLog() {
        String testMessage = "message";
        quadraticEquationLogger.log(testMessage);
        List<String> log = quadraticEquationLogger.getLog();
        String message = log.get(0);
        assertTrue(message.contains(testMessage));
    }

    @Test
    public void canLogSomeMessages() {
        String[] messages = {"1", "2", "3"};
        for (String message : messages) {
            quadraticEquationLogger.log(message);
        }
        List<String> addedMessages = quadraticEquationLogger.getLog();
        for (int i = 0; i < addedMessages.size(); i++) {
            String addedMessage = addedMessages.get(i);
            assertTrue(addedMessage.contains(messages[i]));
        }
    }
}
