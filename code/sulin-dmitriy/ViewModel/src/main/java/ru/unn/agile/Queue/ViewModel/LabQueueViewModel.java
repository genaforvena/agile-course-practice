package ru.unn.agile.Queue.ViewModel;

import ru.unn.agile.Queue.Model.LabQueue;

import java.util.Arrays;
import java.util.List;

public class LabQueueViewModel {

    private final LabQueue<String> queue = new LabQueue<>();
    private int size;
    private String headElement;
    private String element;
    private String result;
    private String operation;
    private boolean isFindButtonEnabled;
    private boolean isPopButtonEnabled;
    private final String errorMessage;
    private final ILabQueueLogger logger;
    public LabQueueViewModel(final ILabQueueLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Error: logger is null");
        }
        this.logger = logger;
        size = 0;
        element = "";
        result = "";
        headElement = "";
        operation = "";
        isFindButtonEnabled = false;
        isPopButtonEnabled = false;
        errorMessage = "Element not found.";
    }

    public boolean isFindButtonEnabled() {
        return isFindButtonEnabled;
    }

    public boolean isPopButtonEnabled() {
        return isPopButtonEnabled;
    }

    public int getSize() {
        return size;
    }

    public void updateSize() {
        size = queue.getSize();
    }

    public String getHeadElement() {
        return headElement;
    }

    public void updateHeadElement() {
        headElement = queue.getHead();
    }

    public String getElement() {
        return element;
    }

    public String getResult() {
        return result;
    }

    public void setElement(final String element) {
        this.element = element;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public void setFindButtonEnabled(final boolean isFindEnabled) {
        isFindButtonEnabled = isFindEnabled;
    }

    public void setPopButtonEnabled(final boolean isPopEnabled) {
        isPopButtonEnabled = isPopEnabled;
    }

    public void setOperation(final String operation) {
        this.operation = operation;
    }

    public List<String> getAllRecords() {
        return logger.getAllRecords();
    }

    public String formRecordForLog() {
        final String record = "Last operation: " + operation
                            + "; Data input: " + element
                            + "; Result: " + result
                            + "; Current size of queue: " + size
                            + "; Current Head of queue: " + headElement;

        return record;
    }

    public void pushElement() {
        queue.push(element);
        updateHeadElement();
        updateSize();
        setFindButtonEnabled(true);
        setPopButtonEnabled(true);
        setOperation(NamesOfOperations.OPERATION_PUSH);
        logger.addRecord(formRecordForLog());
     }

    public void popElement() {
        String tempValue = queue.pop();
        updateHeadElement();
        updateSize();
        setResult(tempValue);
        if (queue.isEmpty()) {
            setFindButtonEnabled(false);
            setPopButtonEnabled(false);
        }
        setOperation(NamesOfOperations.OPERATION_POP);
        logger.addRecord(formRecordForLog());
    }

    public void findElement() {
        String outputMessage;
        String value = getElement();
        try {
            outputMessage = Integer.toString(queue.findElement(value) + 1);
        } catch (Exception exception) {
            outputMessage = errorMessage;
        }
        setResult(outputMessage);
        setOperation(NamesOfOperations.OPERATION_FIND);
        logger.addRecord(formRecordForLog());
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String[] getQueueAsArray() {
        String[] array = queue.convertToStringArray();
        return Arrays.copyOf(array, array.length, String[].class);
    }
}
