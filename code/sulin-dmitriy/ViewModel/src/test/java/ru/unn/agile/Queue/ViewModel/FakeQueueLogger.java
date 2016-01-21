package ru.unn.agile.Queue.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FakeQueueLogger implements ILabQueueLogger {
    private ArrayList<String> records = new ArrayList<String>();

    @Override
    public void addRecord(final String record) {
        records.add(record);
    }

    @Override
    public List<String> getAllRecords() {
        return records;
    }
}
