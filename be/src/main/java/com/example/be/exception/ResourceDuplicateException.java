package com.example.be.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceDuplicateException extends RuntimeException{
    public ResourceDuplicateException(String message) {
        super(message);
    }
}
