package com.challenge.alkemy.api.exception;

/**
 *
 * @author Rodrigo Cruz
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String msg) {
        super(msg);
    }
}
