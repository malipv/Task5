package ru.inno.task5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundReqException extends ResponseStatusException {
    public NotFoundReqException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}