package ru.unn.agile.Minesweeper.Infrastructure;

import ru.unn.agile.Minesweeper.viewmodel.ILogger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.List;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "dd.HH.yyyy HH:mm:ss";
    private final BufferedWriter bufferedWriter;
    private final String nameOfFile;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public TxtLogger(final String nameOfFile) {
        this.nameOfFile = nameOfFile;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(nameOfFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bufferedWriter = logWriter;
    }

    @Override
    public void log(final String s) {
        try {
            bufferedWriter.write(now() + ": " + s + "\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(nameOfFile));
            String logString = bufferedReader.readLine();

            while (logString != null) {
                log.add(logString);
                logString = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
