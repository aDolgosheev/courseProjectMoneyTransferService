package ru.dolgosheev.courseprojectmoneytransferservice.service;

import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.OperationResponse;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;

public interface MoneyTransferService {
    OperationResponse doTransfer(Transfer transfer);

    OperationResponse confirmOperation(ConfirmInfo info);
}
