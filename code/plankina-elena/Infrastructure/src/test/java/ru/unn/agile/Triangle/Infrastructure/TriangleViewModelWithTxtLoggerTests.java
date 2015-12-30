package ru.unn.agile.Triangle.Infrastructure;

import ru.unn.agile.Triangle.TriangleViewModelTests;
import ru.unn.agile.TriangleViewModel.TriangleViewModel;

public class TriangleViewModelWithTxtLoggerTests extends TriangleViewModelTests {
    @Override
    public void setUp() {
        TxtTriangleLogger realLogger = new TxtTriangleLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new TriangleViewModel(realLogger));
    }
}
