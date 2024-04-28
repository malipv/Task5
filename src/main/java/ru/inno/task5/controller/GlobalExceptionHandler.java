package ru.inno.task5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.inno.task5.exceptions.*;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadReqException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> exceptionBadReqException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundReqException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> exceptionNotFoundReqException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(RuntimeException e,
                                                             WebRequest request) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder strOut = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            strOut.append(stackTraceElement.getClassName()).append(" : ").append(stackTraceElement.getMethodName());
        }
        strOut.append(e.getMessage());

        return ResponseEntity
                .status((HttpStatus.INTERNAL_SERVER_ERROR))
                .body(Map.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage() + " : " + strOut.toString()));
    }
}