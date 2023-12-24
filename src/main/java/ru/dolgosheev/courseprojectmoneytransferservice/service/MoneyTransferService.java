package ru.dolgosheev.courseprojectmoneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.dolgosheev.courseprojectmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.dolgosheev.courseprojectmoneytransferservice.exception.InvalidDataException;
import ru.dolgosheev.courseprojectmoneytransferservice.logger.Logger;
import ru.dolgosheev.courseprojectmoneytransferservice.logger.LoggerImpl;
import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.OperationResponse;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class MoneyTransferService {
    private MoneyTransferRepository moneyTransferRepository;
    private Logger logger;

    public MoneyTransferService(MoneyTransferRepository moneyTransferRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.logger = LoggerImpl.getInstance();
    }

    public OperationResponse doTransfer(Transfer transfer) {
        checkValid(transfer);
        transfer.setDate(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        transfer.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.nnn")));
        String id = moneyTransferRepository.addTransfer(transfer);
        logger.log("Try: " + transfer);
        return new OperationResponse(id);
    }

    public OperationResponse confirmOperation(ConfirmInfo info) {
        checkValid(info);
        Transfer transfer;
        if (info.getCode().equals("0000")) {
            transfer = moneyTransferRepository.confirmOperation(info.getOperationId());
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error confirm : " + info);
            }
            logger.log("Confirm: " + transfer);
        } else {
            transfer = moneyTransferRepository.errorConfirmOperation(info.getOperationId());
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error  errorConfirm : " + info);
            }
            logger.log("Error confirm: " + transfer);
        }
        return new OperationResponse(info.getOperationId() + "");
    }

    private void checkValid(Transfer transfer) {
        StringBuilder msg = new StringBuilder();
        boolean flag = true;
        if (transfer.getCardFromCVV() == null || transfer.getCardFromCVV().length() == 0) {
            msg.append("/Invalid CardFromCVV ");
            flag = false;
        }
        if (transfer.getCardFromNumber() == null || transfer.getCardFromNumber().length() == 0) {
            msg.append("/Invalid CardFromNumber ");
            flag = false;
        }
        if (transfer.getCardFromValidTill() == null || transfer.getCardFromValidTill().length() == 0) {
            msg.append("/Invalid CardFromValidTill ");
            flag = false;
        }
        if (transfer.getCardToNumber() == null || transfer.getCardToNumber().length() == 0) {
            msg.append("/Invalid CardToNumber ");
            flag = false;
        }

        if (transfer.getAmount() == null || transfer.getAmount().getValue() < 0 || transfer.getAmount().getCurrency() == null) {
            msg.append("/Invalid CardToNumber ");
            flag = false;
        }

        if (!flag) {
            throw new InvalidDataException("Invalid transfer :" + msg);
        }
    }

    private void checkValid(ConfirmInfo info) {
        StringBuilder msg = new StringBuilder();
        boolean flag = true;
        if (info.getCode() == null || info.getCode().length() == 0) {
            msg.append("/Invalid code");
            flag = false;
        }
        if (info.getOperationId() == null || info.getOperationId().length() == 0) {
            msg.append("/Invalid OperationId ");
            flag = false;
        }

        if (!flag) {
            throw new InvalidDataException("Invalid confirm :" + msg);
        }
    }
}