package ru.unn.agile.QuadraticEquation.infrastructure;

import ru.unn.agile.QuadraticEquationSolver.viewmodel.IQuadraticEquationLogger;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class QuadraticEquationLogger implements IQuadraticEquationLogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String logName;
    private final BufferedWriter bufferedWriter;

    public QuadraticEquationLogger(final String fileName) {
        this.logName = fileName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        bufferedWriter = logWriter;
    }

    private static String timeNow() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    @Override
    public void log(final String message) {
        try {
            String logLine = String.format("%s : %s", timeNow(), message);
            bufferedWriter.write(logLine);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> logFile = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(logName));
            String logLine = bufferedReader.readLine();

            while (logLine != null) {
                logFile.add(logLine);
                logLine = bufferedReader.readLine();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return logFile;
    }
}
