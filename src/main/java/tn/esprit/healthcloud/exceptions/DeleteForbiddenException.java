package tn.esprit.healthcloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DeleteForbiddenException extends RuntimeException {
    public DeleteForbiddenException(String message) {
        super(message);
    }
}
