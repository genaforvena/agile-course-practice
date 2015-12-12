package ru.unn.agile.Minesweeper.Infrastructure;

import ru.unn.agile.Minesweeper.viewmodel.ViewModelTest;
import ru.unn.agile.Minesweeper.viewmodel.ViewModel;

public class ViewModelWithTxtLoggerTest extends ViewModelTest {

    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
