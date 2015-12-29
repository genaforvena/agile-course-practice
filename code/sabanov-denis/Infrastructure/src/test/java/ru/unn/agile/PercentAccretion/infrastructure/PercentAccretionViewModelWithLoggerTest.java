package ru.unn.agile.PercentAccretion.infrastructure;

import ru.unn.agile.PercentAccretion.viewmodel.PercentAccretionViewModel;
import ru.unn.agile.PercentAccretion.viewmodel.PercentAccretionViewModelTest;

public class PercentAccretionViewModelWithLoggerTest extends PercentAccretionViewModelTest {
    @Override
    public void initialize() {
        PercentAccretionLogger realLogger = new PercentAccretionLogger(
                "./PercentAccretionViewModelWithLoggerTest.xml");
        setViewModel(new PercentAccretionViewModel(realLogger));
    }
}
