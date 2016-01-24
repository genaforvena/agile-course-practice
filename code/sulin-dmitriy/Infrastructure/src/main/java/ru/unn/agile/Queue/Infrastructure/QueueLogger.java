package ru.unn.agile.Queue.Infrastructure;

import ru.unn.agile.Queue.ViewModel.ILabQueueLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class QueueLogger implements ILabQueueLogger {
    private final String nameOfFile;
    private static final String CURRENT_TIME = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;

    public QueueLogger(final String nameOfFile) {
        if (nameOfFile == null) {
            throw new IllegalArgumentException("Name of file cannot be empty");
        }
        this.nameOfFile = nameOfFile;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(nameOfFile));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.writer = writer;

    }

    @Override
    public void addRecord(final String record) {
        try {
            writer.write(currentTime() + " >> " + record);
            writer.newLine();
            writer.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<String> getAllRecords() {
        ArrayList<String> records = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(nameOfFile));

            String record = reader.readLine();
            while (record != null) {
                records.add(record);
                record = reader.readLine();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return records;
    }

    static String currentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dataFormat = new SimpleDateFormat(CURRENT_TIME, Locale.ENGLISH);
        return dataFormat.format(calendar.getTime());
    }
}
