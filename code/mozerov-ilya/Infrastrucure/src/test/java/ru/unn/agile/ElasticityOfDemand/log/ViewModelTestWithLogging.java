package ru.unn.agile.ElasticityOfDemand.log;

import ru.unn.agile.ElasticityOfDemand.util.ILogger;
import ru.unn.agile.ElasticityOfDemand.viewmodel.ViewModel;
import ru.unn.agile.ElasticityOfDemand.viewmodel.ViewModelTests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Created by imozerov on 02.03.16.
 */
public class ViewModelTestWithLogging extends ViewModelTests {
    @Override
    public void setUp() {
        try {
            ILogger realLogger = new Logger(
                    new BufferedWriter(
                            new FileWriter("./ViewModel_with_TxtLogger_Tests-lab3.log")));
            setExternalViewModel(new ViewModel(realLogger));
        } catch (IOException e) {
            fail("Unable to create real logger for tests!");
        }
    }
}
