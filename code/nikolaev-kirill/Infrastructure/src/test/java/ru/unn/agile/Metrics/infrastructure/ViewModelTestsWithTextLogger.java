package ru.unn.agile.Metrics.infrastructure;

import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModel;
import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModelTests;

public class ViewModelTestsWithTextLogger extends DistanceCalculatorViewModelTests {
    @Override
    public void setUp() {
        TextLogger realLogger = new TextLogger("./ViewModelTestsWithRealLogger.log");
        setViewModel(new DistanceCalculatorViewModel(realLogger));
    }
}
