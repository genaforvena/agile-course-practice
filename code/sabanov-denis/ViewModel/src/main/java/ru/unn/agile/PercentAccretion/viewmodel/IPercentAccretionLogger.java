package ru.unn.agile.PercentAccretion.viewmodel;

import java.util.List;

public interface IPercentAccretionLogger {
    void log(final String message);

    List<String> getLog();
}
