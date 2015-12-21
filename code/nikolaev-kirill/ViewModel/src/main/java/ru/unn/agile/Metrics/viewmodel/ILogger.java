package ru.unn.agile.Metrics.viewmodel;

import java.util.List;

public interface ILogger {
    void add(String message);
    List<String> get();
}
