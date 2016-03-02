package ru.unn.agile.ElasticityOfDemand.util;

/**
 * Created by imozerov on 02.03.16.
 */
public class NullLogger implements ILogger {

    @Override
    public void log(final String logMessage) {
        // No logging at all!
    }
}
