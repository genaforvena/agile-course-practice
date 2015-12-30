package ru.unn.agile.TriangleViewModel;

import java.util.List;

public interface ILogger {
    void addMessageToLog(final String message);
    List<String> getLog();
}
