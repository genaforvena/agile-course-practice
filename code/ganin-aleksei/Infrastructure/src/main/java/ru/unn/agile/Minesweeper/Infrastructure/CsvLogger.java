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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "dd.HH.yyyy HH:mm:ss";
    private static final String CSV_ROWS = "timestamp, message";
    private static final String CSV_DELIMITER = ", ";
    private static final String CONSOLE_LOG_DELIMITER = ": ";
    private final BufferedWriter bufferedWriter;
    private final String nameOfFile;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    private static String createLogCsvString(final String message) {
        return now() + CSV_DELIMITER + message;
    }

    public CsvLogger(final String nameOfFile) {
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
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void log(final String message) {
        try {
            bufferedWriter.write(createLogCsvString(message) + "\n");
            bufferedWriter.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(nameOfFile));
            bufferedReader.readLine();
            String logString = bufferedReader.readLine();

            Pattern pattern = Pattern.compile("^(?<time>.*)" + CSV_DELIMITER + "(?<message>.*)$");
            while (logString != null) {

                Matcher matcher = pattern.matcher(logString);
                if (matcher.matches()) {
                    String consoleLogString = matcher.group("time")
                                              + CONSOLE_LOG_DELIMITER
                                              + matcher.group("message");
                    System.out.println(consoleLogString);
                    log.add(consoleLogString);
                }
                logString = bufferedReader.readLine();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return log;
    }
}
