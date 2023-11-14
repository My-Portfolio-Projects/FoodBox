package com.foodbox.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    public String status;
    public String message;
    public String time;

}
