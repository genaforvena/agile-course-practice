package ru.unn.agile.PercentAccretion.infrastructure;

import ru.unn.agile.PercentAccretion.viewmodel.IPercentAccretionLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PercentAccretionLogger implements IPercentAccretionLogger {
    private FileWriter fileWriter;
    private final String filePath;

    public PercentAccretionLogger(final String filePath) {
        this.filePath = filePath;

        try {
            fileWriter = new FileWriter(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String logMessage) {
        try {
            fileWriter.write(new Date() + " > " + logMessage + "\n");
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String bufMessage = bufferedReader.readLine();

            while (bufMessage != null) {
                log.add(bufMessage);
                bufMessage = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
