package ru.unn.agile.Minesweeper.Infrastructure;

import ru.unn.agile.Minesweeper.viewmodel.ViewModelTest;
import ru.unn.agile.Minesweeper.viewmodel.ViewModel;

public class ViewModelWithCSVLoggerTest extends ViewModelTest {

    @Override
    public void setUp() {
        CSVLogger realLogger = new CSVLogger("./ViewModelWithCSVLoggerOfTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
