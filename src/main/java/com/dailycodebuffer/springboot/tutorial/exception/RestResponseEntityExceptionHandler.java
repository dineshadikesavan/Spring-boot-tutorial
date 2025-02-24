package com.dailycodebuffer.springboot.tutorial.exception;

import com.dailycodebuffer.springboot.tutorial.entity.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DepartmentNotFoundException.class})
    public ResponseEntity<ExceptionMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest request) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionMessage);
    }

}
