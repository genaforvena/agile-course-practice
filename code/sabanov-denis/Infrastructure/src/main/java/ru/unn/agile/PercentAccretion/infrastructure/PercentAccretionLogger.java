package ru.unn.agile.PercentAccretion.infrastructure;

import ru.unn.agile.PercentAccretion.viewmodel.IPercentAccretionLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PercentAccretionLogger implements IPercentAccretionLogger {
    private FileWriter fileWriter;
    private final String filePath;

    public PercentAccretionLogger(final String filePath) {
        this.filePath = filePath;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String logMessage) {
        try {
            fileWriter.write("  <data=\"" + "[" + new Date() + "]"
                    + "\"/> <logMessage=\"" + logMessage + "\" />" + "\n");
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<>();
        Pattern pattern = Pattern.compile("^  <data=\"(?<date>.*)"
                + "\"/> <logMessage=\"(?<logMessage>.*)\" />$");
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String bufMessage = bufferedReader.readLine();

            while (bufMessage != null) {
                Matcher matcher = pattern.matcher(bufMessage);
                if (matcher.matches()) {
                    log.add(matcher.group("date") + " " + matcher.group("logMessage"));
                }
                bufMessage = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return log;
    }
}
