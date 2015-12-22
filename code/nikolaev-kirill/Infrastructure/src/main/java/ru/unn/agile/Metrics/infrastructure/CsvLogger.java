package ru.unn.agile.Metrics.infrastructure;

import ru.unn.agile.Metrics.viewmodel.ILogger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CsvLogger implements ILogger {
    private final FileWriter writer;
    private final String fileName;

    public CsvLogger(final String fileName) {
        this.fileName = fileName;
        FileWriter logWriter = null;
        try {
            logWriter = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void add(final String message) {
        try {
            writer.write(getCurrentDateAndTime() + ",\"" + message + "\"" + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        ArrayList<String> log = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }

    private String getCurrentDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
