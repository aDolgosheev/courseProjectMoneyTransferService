package ru.dolgosheev.courseprojectmoneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConfirmInfo {
    private String code;
    private String operationId;

    @Override
    public String toString() {
        return "code: " + code +
                "/ operationId: " + operationId;
    }
}
