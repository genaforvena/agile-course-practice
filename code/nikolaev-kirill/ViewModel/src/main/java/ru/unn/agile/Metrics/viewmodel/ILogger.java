package ru.unn.agile.Metrics.viewmodel;

import java.util.ArrayList;

public interface ILogger {
    void add(String message);
    ArrayList<String> getLog();
    String getLastMessage();
}
