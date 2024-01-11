package ru.dolgosheev.courseprojectmoneytransferservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Amount {

    private Float value;

    public Amount(Long value) {
        this.value = (float) (value / 100);
    }

    public void setValue(Long value) {
        this.value = (float) (value / 100);
    }

    @Override
    public String toString() {
        return value + " руб.";
    }
}
