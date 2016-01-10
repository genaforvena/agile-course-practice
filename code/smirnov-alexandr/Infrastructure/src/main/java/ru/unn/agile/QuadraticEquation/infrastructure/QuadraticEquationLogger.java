package ru.unn.agile.QuadraticEquation.infrastructure;

import ru.unn.agile.QuadraticEquationSolver.viewmodel.IQuadraticEquationLogger;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class QuadraticEquationLogger implements IQuadraticEquationLogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String logName;
    private final BufferedWriter bufferedWriter;

    public QuadraticEquationLogger(final String filename) {
        this.logName = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufferedWriter = logWriter;
    }

    private static String timeNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void log(final String message) {
        try {
            bufferedWriter.write(timeNow() + " : " + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> logFile = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(logName));
            String txtLine = bufferedReader.readLine();

            while (txtLine != null) {
                logFile.add(txtLine);
                txtLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logFile;
    }
}
