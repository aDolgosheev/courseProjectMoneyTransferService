package ru.dolgosheev.courseprojectmoneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.dolgosheev.courseprojectmoneytransferservice.model.Transfer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MoneyTransferRepository{
    private ConcurrentMap<String, MoneyTransferState> transferStateMap;
    private ConcurrentMap<String, Transfer> transferList;
    private AtomicInteger id;

    public MoneyTransferRepository() {
        this.transferStateMap = new ConcurrentHashMap<>();
        this.transferList = new ConcurrentHashMap<>();
        id = new AtomicInteger(0);
    }

    public String addTransfer(Transfer transfer) {
        String transferId = id.incrementAndGet() + "";
        transferStateMap.put(transferId + "", MoneyTransferState.LOAD);
        transferList.put(transferId + "", transfer);
        return transferId;

    }

    public Transfer confirmOperation(String id) {
        if(!transferStateMap.containsKey(id)){
            return null;
        }
        transferStateMap.put(id, MoneyTransferState.OK);
        return transferList.get(id);
    }

    public Transfer errorConfirmOperation(String id) {
        if(!transferStateMap.containsKey(id)){
            return null;
        }
        transferStateMap.put(id, MoneyTransferState.ERROR);
        return transferList.get(id);
    }

    public MoneyTransferState getTransferState(String id){
        return transferStateMap.get(id);
    }
}