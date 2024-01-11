package ru.dolgosheev.courseprojectmoneytransferservice.service;

import ru.dolgosheev.courseprojectmoneytransferservice.exception.InvalidDataException;
import ru.dolgosheev.courseprojectmoneytransferservice.model.ConfirmInfo;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;

public class ValidationCheck {

    public static void checkValid(Transfer transfer) {
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

        if (transfer.getAmount() == null || transfer.getAmount().getValue() < 0) {
            msg.append("/Invalid CardToNumber ");
            flag = false;
        }

        if (!flag) {
            throw new InvalidDataException("Invalid transfer :" + msg);
        }
    }

    public static void checkValid(ConfirmInfo info) {
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
