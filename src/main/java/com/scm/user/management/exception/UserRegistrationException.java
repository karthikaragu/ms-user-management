package com.scm.user.management.exception;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationException extends RuntimeException {

    private final int status;

    public UserRegistrationException(int status, String message){
        super(message);
        this.status = status;
    }

    public UserRegistrationException(){
        this.status = HttpStatus.SC_NOT_ACCEPTABLE;
    }

    public int getStatus() {
        return status;
    }

}
