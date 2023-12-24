package ru.dolgosheev.courseprojectmoneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dolgosheev.courseprojectmoneytransferservice.model.*;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferRepository;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferState;
import ru.dolgosheev.courseprojectmoneytransferservice.service.MoneyTransferService;

@SpringBootTest
class CourseProjectMoneyTransferServiceApplicationTests {

    static MoneyTransferService moneyTransferService;
    static MoneyTransferRepository moneyTransferRepository;

    @BeforeAll
    static void init() {
        moneyTransferRepository = new MoneyTransferRepository();
        moneyTransferService = new MoneyTransferService(moneyTransferRepository);
    }

    @Test
    void serviceDoTransferTest() {

        Transfer transfer = new Transfer(
                new Amount(Currency.RUR, 500L),
                "555",
                "0000111122223333",
                "12/23",
                "5555666677778888"
        );

        OperationResponse id = moneyTransferService.doTransfer(transfer);

        Assertions.assertEquals(id.getOperationId(), 1 + "");
    }

    @Test
    void serviceConfirmOperationTest() {
        ConfirmInfo confirmInfo = Mockito.spy(ConfirmInfo.class);
        Mockito.when(confirmInfo.getOperationId()).thenReturn("1");
        Mockito.when(confirmInfo.getCode()).thenReturn("0000");

        OperationResponse id = moneyTransferService.confirmOperation(confirmInfo);

        Assertions.assertEquals(id.getOperationId(), 1 + "");
    }

    @Test
    void serviceConfirmTransferStateTest() {
        MoneyTransferState state = moneyTransferRepository.getTransferState("1");
        Assertions.assertEquals(state, MoneyTransferState.OK);
    }
}
