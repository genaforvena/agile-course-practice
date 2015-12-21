package ru.unn.agile.Metrics.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<>();
    @Override
    public void add(final String message) {
        log.add(message);
    }

    @Override
    public List<String> get() {
        return log;
    }
}
