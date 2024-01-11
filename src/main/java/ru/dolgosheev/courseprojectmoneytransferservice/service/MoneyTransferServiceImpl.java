package ru.dolgosheev.courseprojectmoneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.dolgosheev.courseprojectmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.dolgosheev.courseprojectmoneytransferservice.logger.Logger;
import ru.dolgosheev.courseprojectmoneytransferservice.logger.LoggerImpl;
import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.OperationResponse;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    private MoneyTransferRepositoryImpl moneyTransferRepositoryImpl;
    private Logger logger;

    public MoneyTransferServiceImpl(MoneyTransferRepositoryImpl moneyTransferRepositoryImpl) {
        this.moneyTransferRepositoryImpl = moneyTransferRepositoryImpl;
        this.logger = LoggerImpl.getInstance();
    }

    @Override
    public OperationResponse doTransfer(Transfer transfer) {
        ValidationCheck.checkValid(transfer);
        transfer.setDate(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        transfer.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.nnn")));
        String id = moneyTransferRepositoryImpl.addTransfer(transfer);
        logger.log("Try: " + transfer);
        return new OperationResponse(id);
    }

    @Override
    public OperationResponse confirmOperation(ConfirmInfo info) {
        ValidationCheck.checkValid(info);
        Transfer transfer;
        if (info.getCode().equals("0000")) {
            transfer = moneyTransferRepositoryImpl.confirmOperation(info.getOperationId());
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error confirm : " + info);
            }
            logger.log("Confirm: " + transfer);
        } else {
            transfer = moneyTransferRepositoryImpl.errorConfirmOperation(info.getOperationId());
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error  errorConfirm : " + info);
            }
            logger.log("Error confirm: " + transfer);
        }
        return new OperationResponse(info.getOperationId() + "");
    }

}