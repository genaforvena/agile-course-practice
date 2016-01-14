package ru.unn.agile.Queue.Infrastructure;

import ru.unn.agile.Queue.ViewModel.LabQueueViewModel;
import ru.unn.agile.Queue.ViewModel.OtherViewModelTests;

public class QueueViewModelWithLoggerTests extends OtherViewModelTests {
    @Override
    public void setUpViewModel() {
        QueueLogger logger = new QueueLogger("./QueueViewModelWithLoggerTests.txt");
        super.setViewModel(new LabQueueViewModel(logger));
    }
}
