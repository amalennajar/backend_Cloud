package tn.esprit.healthcloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DayOffNotFoundException extends RuntimeException {
    public DayOffNotFoundException(String message) {
        super(message);
    }
}

