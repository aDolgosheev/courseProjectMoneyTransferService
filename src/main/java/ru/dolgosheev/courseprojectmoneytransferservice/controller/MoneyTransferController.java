package ru.dolgosheev.courseprojectmoneytransferservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.OperationResponse;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferRepository;
import ru.dolgosheev.courseprojectmoneytransferservice.service.MoneyTransferService;

@RestController
@CrossOrigin(origins = "https://serp-ya.github.io/card-transfer/", allowedHeaders = "*")
public class MoneyTransferController {
    private MoneyTransferService moneyTransferService;

    public MoneyTransferController() {
        MoneyTransferRepository repository = new MoneyTransferRepository();
        this.moneyTransferService = new MoneyTransferService(repository);
    }

    @PostMapping("transfer")
    public OperationResponse doTransfer(@RequestBody Transfer transfer) {
        return moneyTransferService.doTransfer(transfer);
    }

    @PostMapping("confirmOperation")
    public OperationResponse confirmOperation(@RequestBody ConfirmInfo info) {
        return moneyTransferService.confirmOperation(info);
    }
}
