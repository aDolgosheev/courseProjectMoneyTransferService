package ru.dolgosheev.courseprojectmoneytransferservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.OperationResponse;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;

public interface MoneyTransferController {
    @PostMapping("transfer")
    OperationResponse doTransfer(@RequestBody Transfer transfer);

    @PostMapping("confirmOperation")
    OperationResponse confirmOperation(@RequestBody ConfirmInfo info);
}
