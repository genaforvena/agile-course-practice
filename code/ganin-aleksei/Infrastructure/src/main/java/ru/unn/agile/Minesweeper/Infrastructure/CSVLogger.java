package ru.unn.agile.Minesweeper.Infrastructure;

import ru.unn.agile.Minesweeper.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CSVLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "dd.HH.yyyy HH:mm:ss";
    private static final String CSV_ROWS = "timestamp, message";
    private static final int TIMESTAMP_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;
    private final BufferedWriter bufferedWriter;
    private final String nameOfFile;

    public class LogRecord {
        private final String time;
        private final String message;
        public String getTime() {
            return time;
        }
        public String getMessage() {
            return message;
        }
        public LogRecord(final String time, final String message) {
            this.time = time;
            this.message = message;
        }
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public CSVLogger(final String nameOfFile) {
        this.nameOfFile = nameOfFile;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(nameOfFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufferedWriter = logWriter;
        try {
            bufferedWriter.write(CSV_ROWS + "\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void log(final String s) {
        try {
            bufferedWriter.write(now() + ", " + s + "\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private LogRecord getLogRecord(final String logString) {
        String [] logRecordFields = logString.split(", ");
        return new LogRecord(logRecordFields[TIMESTAMP_INDEX], logRecordFields[MESSAGE_INDEX]);
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(nameOfFile));
            bufferedReader.readLine();
            String logString = bufferedReader.readLine();

            while (logString != null) {
                LogRecord logRecord = getLogRecord(logString);
                log.add(logRecord.getTime() + ": " + logRecord.getMessage());
                logString = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
