package ru.unn.agile.TriangleViewModel;

import java.util.List;

public interface ILogger {
    void add(final String newMessage);
    List<String> getLog();
}
