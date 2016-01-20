package ru.unn.agile.Queue.ViewModel;

import java.util.List;

public interface ILabQueueLogger {
    void addRecord(final String newRecord);
    List<String> getAllRecords();
}
