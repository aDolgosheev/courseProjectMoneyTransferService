package ru.dolgosheev.courseprojectmoneytransferservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.OperationResponse;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;
import ru.dolgosheev.courseprojectmoneytransferservice.service.MoneyTransferServiceImpl;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "url", allowedHeaders = "*")
public class MoneyTransferControllerImpl implements MoneyTransferController {
    private final MoneyTransferServiceImpl moneyTransferServiceImpl;

//    public MoneyTransferController() {
//        MoneyTransferRepository repository = new MoneyTransferRepository();
//        this.moneyTransferService = new MoneyTransferService(repository);
//    }

    @Override
    @PostMapping("transfer")
    public OperationResponse doTransfer(@RequestBody Transfer transfer) {
        return moneyTransferServiceImpl.doTransfer(transfer);
    }

    @Override
    @PostMapping("confirmOperation")
    public OperationResponse confirmOperation(@RequestBody ConfirmInfo info) {
        return moneyTransferServiceImpl.confirmOperation(info);
    }
}
