package ru.unn.agile.Metrics.viewmodel;

import java.util.ArrayList;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<>();
    @Override
    public void add(final String message) {
        log.add(message);
    }

    @Override
    public ArrayList<String> getLog() {
        return log;
    }

}
