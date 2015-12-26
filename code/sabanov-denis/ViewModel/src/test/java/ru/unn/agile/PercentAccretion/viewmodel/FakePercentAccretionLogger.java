package ru.unn.agile.PercentAccretion.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakePercentAccretionLogger implements IPercentAccretionLogger {
    private ArrayList<String> log;

    public FakePercentAccretionLogger() {
        log = new ArrayList<>();
    }

    @Override
    public void log(final String message) {
        log.add(message);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
