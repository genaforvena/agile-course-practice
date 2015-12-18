package ru.unn.agile.Metrics.infrastructure;

import ru.unn.agile.Metrics.viewmodel.ILogger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TextLogger implements ILogger {
    private static final byte NEW_LINE = 0xA;
    private static final byte CARRIAGE_RETURN = 0xD;
    private final BufferedWriter writer;
    private final String fileName;

    public TextLogger(final String fileName) {
        this.fileName = fileName;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void add(final String message) {
        try {
            writer.write(getCurrentDateAndTime() + " > " + message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getLog() {
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

    @Override
    public String getLastMessage() {
        String lastMessage = "";
        try {
            RandomAccessFile reader = new RandomAccessFile(fileName, "r");
            long length = reader.length() - 1;
            StringBuilder builder = new StringBuilder();
            for (long filePointer = length; filePointer != -1; filePointer--) {
                reader.seek(filePointer);
                byte readByte = reader.readByte();
                if (readByte == NEW_LINE) {
                    if (filePointer == length) {
                        continue;
                    }
                    break;
                } else if (readByte == CARRIAGE_RETURN) {
                    if (filePointer == length - 1) {
                        continue;
                    }
                    break;
                }
                builder.append((char) readByte);
            }
            lastMessage = builder.reverse().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastMessage;
    }

    private String getCurrentDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
