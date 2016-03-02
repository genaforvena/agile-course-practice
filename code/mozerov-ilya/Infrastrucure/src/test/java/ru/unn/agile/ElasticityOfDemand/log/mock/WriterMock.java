package ru.unn.agile.ElasticityOfDemand.log.mock;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imozerov on 02.03.16.
 */
public class WriterMock extends Writer {
    private final List<String> logMessages = new ArrayList<>();

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        logMessages.add(String.copyValueOf(cbuf));
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }

    public List<String> getLogMessages() {
        return logMessages;
    }
}
