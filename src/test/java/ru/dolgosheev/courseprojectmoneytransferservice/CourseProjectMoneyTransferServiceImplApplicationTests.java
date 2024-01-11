package ru.dolgosheev.courseprojectmoneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dolgosheev.courseprojectmoneytransferservice.model.*;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferRepositoryImpl;
import ru.dolgosheev.courseprojectmoneytransferservice.repository.MoneyTransferState;
import ru.dolgosheev.courseprojectmoneytransferservice.service.MoneyTransferServiceImpl;

@SpringBootTest
class CourseProjectMoneyTransferServiceImplApplicationTests {

    static MoneyTransferServiceImpl moneyTransferServiceImpl;
    static MoneyTransferRepositoryImpl moneyTransferRepositoryImpl;

    @BeforeAll
    static void init() {
        moneyTransferRepositoryImpl = new MoneyTransferRepositoryImpl();
        moneyTransferServiceImpl = new MoneyTransferServiceImpl(moneyTransferRepositoryImpl);
    }

    @Test
    void serviceDoTransferTest() {

        Transfer transfer = new Transfer(
                new Amount(500L),
                "555",
                "0000111122223333",
                "12/23",
                "5555666677778888"
        );

        OperationResponse id = moneyTransferServiceImpl.doTransfer(transfer);

        Assertions.assertEquals(id.getOperationId(), 1 + "");
    }

    @Test
    void serviceConfirmOperationTest() {
        ConfirmInfo confirmInfo = Mockito.spy(ConfirmInfo.class);
        Mockito.when(confirmInfo.getOperationId()).thenReturn("1");
        Mockito.when(confirmInfo.getCode()).thenReturn("0000");

        OperationResponse id = moneyTransferServiceImpl.confirmOperation(confirmInfo);

        Assertions.assertEquals(id.getOperationId(), 1 + "");
    }

    @Test
    void serviceConfirmTransferStateTest() {
        MoneyTransferState state = moneyTransferRepositoryImpl.getTransferState("1");
        Assertions.assertEquals(state, MoneyTransferState.OK);
    }
}
