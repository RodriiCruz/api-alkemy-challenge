package com.challenge.alkemy.api.exception;

/**
 *
 * @author Rodrigo Cruz
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
