package ru.unn.agile.Queue.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FakeQueueLogger implements ILabQueueLogger {
    private ArrayList<String> records = new ArrayList<String>();

    @Override
    public void addRecord(final String newRecord) {
        records.add(newRecord);
    }

    @Override
    public List<String> getAllRecords() {
        return records;
    }
}
