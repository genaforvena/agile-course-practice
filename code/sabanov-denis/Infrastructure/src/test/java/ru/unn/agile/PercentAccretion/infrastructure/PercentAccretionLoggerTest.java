package ru.unn.agile.PercentAccretion.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PercentAccretionLoggerTest {
    private static final String PATH_TO_FILE = "./PercentAccretionLoggerTest.log";
    private PercentAccretionLogger logger;

    @Before
    public void initialize() {
        logger = new PercentAccretionLogger(PATH_TO_FILE);
    }

    @Test
    public void canCreatePercentAccretionLogger() {
        assertNotNull(logger);
    }

    @Test
    public void checkByDefaultLogIsEmpty() {
        assertTrue(logger.getLog().isEmpty());
    }

    @Test
    public void checkLoggerCreateTxtFileOnDisk() {
        assertTrue(new File(PATH_TO_FILE).isFile());
    }

    @Test
    public void checkLogMethodAddSomethingInLog() {
        logger.log("something");

        assertFalse(logger.getLog().isEmpty());
    }

    @Test
    public void checkCanAddSeveralMessagesInLog() {
        logger.log("bla bla");
        logger.log("bla bla bla");

        assertEquals(2, logger.getLog().size());
    }
}
