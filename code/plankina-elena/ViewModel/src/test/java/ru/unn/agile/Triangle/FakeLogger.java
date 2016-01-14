package ru.unn.agile.Triangle;

import ru.unn.agile.TriangleViewModel.ILogger;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void add(final String newMessage) {
        log.add(newMessage);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
