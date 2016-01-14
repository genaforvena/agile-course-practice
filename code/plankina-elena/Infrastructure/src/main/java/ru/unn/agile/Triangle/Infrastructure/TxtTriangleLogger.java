package ru.unn.agile.Triangle.Infrastructure;

import ru.unn.agile.TriangleViewModel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtTriangleLogger implements ILogger {
    private final String fileName;
    private BufferedWriter bufferedWriter;

    public TxtTriangleLogger(final String fileName) {
        this.fileName = fileName;
        try {
            File logFile = new File(fileName);
            FileOutputStream outputStreamForLogFile = new FileOutputStream(logFile);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStreamForLogFile));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void add(final String newMessage) {
        try {
            bufferedWriter.write("[" + getDateAndTime() + "] " + newMessage);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            File logFile = new File(fileName);
            FileInputStream inputStreamForLogFile = new FileInputStream(logFile);
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStreamForLogFile));
            String currentRecord = bufferedReader.readLine();
            while (currentRecord != null) {
                log.add(currentRecord);
                currentRecord = bufferedReader.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return log;
    }

    private String getDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }
}
