package ru.unn.agile.Minesweeper.viewmodel;

import java.util.List;
import java.util.ArrayList;

public class FakeLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void log(final String s) {
        log.add(s);
    }

}
