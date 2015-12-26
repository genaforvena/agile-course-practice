package ru.unn.agile.PercentAccretion.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PercentAccretionLoggerTest {
    private static final String PATH_TO_FILE = "./PercentAccretionLoggerTest.log";
    private PercentAccretionLogger logger;

    @Before
    public void initializeLogger() {
        logger = new PercentAccretionLogger(PATH_TO_FILE);
    }

    @Test
    public void canCreatePercentAccretionLogger() {
        assertNotNull(logger);
    }

    @Test
    public void byDefaultLogIsEmpty() {
        assertTrue(logger.getLog().isEmpty());
    }

    @Test
    public void didLoggerCreateTxtFileOnDisk() {
        assertTrue(new File(PATH_TO_FILE).isFile());
    }

    @Test
    public void doesLogMethodAddSomethingInLog() {
        logger.log("test");

        assertFalse(logger.getLog().isEmpty());
    }

    @Test
    public void canAddSeveralMessagesInLog() {
        logger.log("test");
        logger.log("test2");

        assertEquals(2, logger.getLog().size());
    }
}
