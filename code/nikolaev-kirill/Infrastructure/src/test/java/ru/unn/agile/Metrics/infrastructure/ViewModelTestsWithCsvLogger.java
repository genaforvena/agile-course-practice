package ru.unn.agile.Metrics.infrastructure;

import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModel;
import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModelTests;

public class ViewModelTestsWithCsvLogger extends DistanceCalculatorViewModelTests {
    @Override
    public void setUp() {
        CsvLogger realLogger = new CsvLogger("./ViewModelTestsWithRealLogger.csv");
        setViewModel(new DistanceCalculatorViewModel(realLogger));
    }
}
