package ru.unn.agile.ElasticityOfDemand.log;

import ru.unn.agile.ElasticityOfDemand.util.ILogger;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Logger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final Writer writer;

    public Logger(final Writer writer) {
        this.writer = writer;
    }

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    @Override
    public void log(final String logMessage) {
        try {
            writer.write(now() + " > " + logMessage + System.lineSeparator());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
