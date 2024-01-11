package ru.dolgosheev.courseprojectmoneytransferservice.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private String message;
    private Integer Id;

//    public ErrorResponse(String message, Integer id) {
//        this.message = message;
//        Id = id;
//    }

}
