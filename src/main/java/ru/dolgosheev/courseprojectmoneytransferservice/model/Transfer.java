package ru.dolgosheev.courseprojectmoneytransferservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class Transfer {
    private Amount amount;
    private String cardFromCVV;
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardToNumber;
    private String date;
    private String time;

    public Transfer(Amount amount, String cardFromCVV, String cardFromNumber, String cardFromValidTill, String cardToNumber) {
        this.amount = amount;
        this.cardFromCVV = cardFromCVV;
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardToNumber = cardToNumber;
    }

    @Override
    public String toString() {
        return "Transfer -> " +
                " amount: " + amount.toString() +
                "/ cardFromNumber: " + cardFromNumber +
                "/ cardToNumber: " + cardToNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer transfer)) return false;
        return Objects.equals(amount, transfer.amount) && Objects.equals(cardFromCVV, transfer.cardFromCVV) && Objects.equals(cardFromNumber, transfer.cardFromNumber) && Objects.equals(cardFromValidTill, transfer.cardFromValidTill) && Objects.equals(cardToNumber, transfer.cardToNumber) && Objects.equals(date, transfer.date) && Objects.equals(time, transfer.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, cardFromCVV, cardFromNumber, cardFromValidTill, cardToNumber, date, time);
    }
}
