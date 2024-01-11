package ru.dolgosheev.courseprojectmoneytransferservice.repository;

import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;

public interface MoneyTransferRepository {
    String addTransfer(Transfer transfer);

    Transfer confirmOperation(String id);

    Transfer errorConfirmOperation(String id);

    MoneyTransferState getTransferState(String id);
}
